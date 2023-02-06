package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import entities.Item;
import entities.Location;
import game_state.GameState;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

//Class that parses the JSON and starts the game
public class LocationParser {
    private static JsonNode rooms;
    private static List<String> pickedUpItems;
    private static String[] hands = new String[1];

    public static final String underline = "\u001b[4m";
    public static final String bold = "\033[1m";
    public static final String unBold = "\033[0m";
    public static final String RED = "\u001b[31;1m";

    private static String currentRoom = "Beach";


    public static void Run() throws IOException {
        Scanner sc = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream input = LocationParser.class.getClassLoader().getResourceAsStream("locations.json")) {
            List<Location> locations = mapper.readValue(input, new TypeReference<List<Location>>() {
            });
            Map<String, Location> locationMap = locations
                    .stream()
                    .collect(Collectors.toMap(Location::getName, Function.identity()));
            System.out.println(locations);
        }

        //original way to read the information and move through the JSON
        try {
            rooms = mapper.readTree(new File("src/main/resources/game-info.json"));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentRoom = "Beach";
        String currentItem = "item";
        String quitGame = "quit";
        boolean gameRun = true;
        String[] itemList = new String[1];
        List<Item> items = new ArrayList<>();

        while (gameRun) {
            JsonNode room = rooms.get(currentRoom);

            System.out.println(room.get("description").asText());

            if (room.has("item")) {
                System.out.println(GameState.CYAN + bold + "[Items at this location:]" + unBold + GameState.RESET);
                for (JsonNode item : room.get("item")) {
                    System.out.println(item.get("name").asText());
                }
            } else {
                System.out.println(GameState.CYAN + bold + "[There are no items at this location]" + unBold + GameState.RESET);
            }

            System.out.print("\nWhich direction would you like to go? [Hint: You can type 'help' at any time to view a list of commands] ");

            String action = sc.nextLine();


            if (action.contains("look") && room.has("item")) {
                for (JsonNode item : room.get("item")) {
                    if (item.get("name").asText().toLowerCase().equals(action.substring(5))) {
                        System.out.println(item.get("description").asText());
                    }
                }
            }


            if (action.startsWith("pickup")) {
                String[] item = action.split(" ");
                pickUp(item[1]);
            }

            if (action.equals("quit")) {
                System.out.println(RED + "\nAre you sure you want to quit? Yes or No?" + GameState.RESET);
                action = sc.nextLine().toLowerCase();
                if (action.equals("no")) {
                    continue;
                } else {
                    System.out.println(GameState.quitMessage());
                    gameRun = false;
                    break;
                }
            }

            if (action.equals("help")) {
                System.out.println(underline + "\nHere are the available commands: " + GameState.RESET);
                System.out.println("-Type" + GameState.CYAN + bold + " 'go' (direction)" + unBold + " => Example: go north" + GameState.RESET);
                System.out.println("-Type" + GameState.CYAN + bold + " 'pickup' (item)" + unBold + " => Example: pickup flare gun" + GameState.RESET);
                System.out.println("-Type" + GameState.CYAN + bold + " 'quit'" + unBold + " => Quits Game\n" + GameState.RESET);
                continue;
            }

            if (!action.contains("go")) {
                System.out.println("That's not a complete response. Please try again.");
                System.out.println();
                System.out.println();
                continue;
            }


            String[] word = action.split(" ");
            String direction = word[1];


            if (!room.has(direction)) {
                System.out.println(RED + "You can't go in that direction. Try a different way.\n" + GameState.RESET);
                continue;
            }
            currentRoom = room.get(direction).asText();

        }
    }

    private static void pickUp(String itemName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("src/main/resources/game-info.json"));

        JsonNode room = root.get(currentRoom);
        ArrayNode items = (ArrayNode) room.get("item");

        int itemIndex = -1;

        for (int i = 0; i < items.size(); i++) {
            JsonNode item = items.get(i);
            if (item.get("name").asText().equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        if (itemIndex != -1) {
            items.remove(itemIndex);
        }
        mapper.writeValue(new File("src/main/resources/game-info.json"), root);
    }

    //
    //public void help
    //public void quit
    //public void look
    //public void go
    //master method w/switch case

}