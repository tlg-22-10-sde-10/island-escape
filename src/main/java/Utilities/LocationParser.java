package Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import entities.BackPack;
import entities.Item;
import game_state.GameState;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LocationParser {
    private static JsonNode rooms;
    private static List<String> pickedUpItems;
    private static String[] hands = new String[1];

    public static void Run() {
        Scanner sc = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();

        try {
            rooms = mapper.readTree(new File("src/main/resources/game-info.json"));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String currentRoom = "Beach";
        String currentItem = "item";
        String quitGame = "quit";
        boolean gameRun = true;
        String[] itemList = new String[1];
        List<Item> items = new ArrayList<>();

        while (gameRun) {
            JsonNode room = rooms.get(currentRoom);

            System.out.println(room.get("description").asText());
            if(room.has("item")){
                System.out.println("There are the following items here: ");
                for(JsonNode item : room.get("item")){
                    System.out.println(item.get("name").asText());
                }
            }
            else {
                System.out.println("There are no items at this location");
            }

            System.out.print("Which direction would you like to go?:\n");
            System.out.println("Type 'help' to see a list of commands > ");

            String action = sc.nextLine();


            if(action.equals("quit")){
                System.out.println("Are you sure you want to quit? Yes or No?");
                action = sc.nextLine().toLowerCase();
                if(action.equals("no")){
                    continue;
                } else{
                    System.out.println(GameState.quitMessage());
                    gameRun = false;
                    break;
                }
            }

            if(action.equals("help")){
                System.out.println("\nHere are the available commands: ");
                System.out.println("-Type 'go' (direction) Example: go north");
                System.out.println("-Type 'pickup' (item) Example: pickup flare gun");
                System.out.println("-Type 'quit' (To quit game) \n");
                continue;
            }

            if(!action.contains("go")){
                System.out.println("Invalid Input");
                System.out.println();
                System.out.println();
                continue;
            }

            String[] word = action.split(" ");
            String direction = word[1];

            if (!room.has(direction)) {
                System.out.println("You can't go in that direction.");
                continue;
            }
            currentRoom = room.get(direction).asText();

        }
    }



}