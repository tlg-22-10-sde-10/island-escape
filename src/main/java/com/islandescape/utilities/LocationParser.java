package com.islandescape.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.islandescape.entities.Item;
import com.islandescape.entities.Location;
import com.islandescape.controllers.GameInteractions;

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
    private static final String ALT_FILE = "src/main/resources/game-info1.json";
    public static final String underline = "\u001b[4m";
    public static final String bold = "\033[1m";
    public static final String unBold = "\033[0m";
    public static final String RED = "\u001b[31;1m";

    private static String currentRoom = "Beach";


    public static void Run() throws IOException {
        Scanner sc = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
//commenting out original stream
//        try (InputStream input = LocationParser.class.getClassLoader().getResourceAsStream("locations.json")) {
//            List<Location> locations = mapper.readValue(input, new TypeReference<List<Location>>() {
//            });
//            Map<String, Location> locationMap = locations
//                    .stream()
//                    .collect(Collectors.toMap(Location::getName, Function.identity()));
//            //System.out.println(locations);
//        }

        Map<String, Location> locationMap;
        try (InputStream input = LocationParser.class.getClassLoader().getResourceAsStream("locations.json")) {
            List<Location> locations = mapper.readValue(input, new TypeReference<List<Location>>() {
            });
            locationMap = locations
                    .stream()
                    .collect(Collectors.toMap(Location::getName, Function.identity()));
        }
        Map<String, Location> map = locationMap;


        //original way to read the information and move through the JSON


        currentRoom = "Beach";
        String currentItem = "item";
        String quitGame = "quit";
        boolean gameRun = true;
        String[] itemList = new String[1];
        List<Item> items = new ArrayList<>();

        while (gameRun) {
            Location room =  map.get(currentRoom);

            //System.out.println(room.get("description").asText());
            System.out.println(room.getDescription());

            if (room.getItems() != null) {
                System.out.println(GameInteractions.CYAN + bold + "[Items at this location:]" + unBold + GameInteractions.RESET);
                for (Item item : room.getItems()) {
                    System.out.println(item.getName());
                }
            } else {
                System.out.println(GameInteractions.CYAN + bold + "[There are no items at this location]" + unBold + GameInteractions.RESET);
            }

            System.out.print("\nWhich direction would you like to go? [Hint: You can type 'help' at any time to view a list of commands] ");

            String action = sc.nextLine();


            if (action.contains("look") && room.getItems() != null) {
                for (Item item : room.getItems()) {
                    if (item.getName().toLowerCase().equals(action.substring(5))) {
                        System.out.println(item.getDescription());
                    }
                }
            }


            if (action.contains("pickup")) {
                String[] item = action.split(" ");
                pickUp(locationMap,currentRoom,item[1]);
            }

            if (action.equals("quit")) {
                System.out.println(RED + "\nAre you sure you want to quit? Yes or No?" + GameInteractions.RESET);
                action = sc.nextLine().toLowerCase();
                if (action.equals("no")) {
                    continue;
                } else {
                    System.out.println(GameInteractions.quitMessage());
                    gameRun = false;
                    break;
                }
            }

            if (action.equals("help")) {
                System.out.println(underline + "\nHere are the available commands: " + GameInteractions.RESET);
                System.out.println("-Type" + GameInteractions.CYAN + bold + " 'go' (direction)" + unBold + " => Example: go north" + GameInteractions.RESET);
                System.out.println("-Type" + GameInteractions.CYAN + bold + " 'pickup' (item)" + unBold + " => Example: pickup flare gun" + GameInteractions.RESET);
                System.out.println("-Type" + GameInteractions.CYAN + bold + " 'quit'" + unBold + " => Quits Game\n" + GameInteractions.RESET);
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


            if (getCurrentRoom(direction,room)== null) {
                System.out.println(RED + "You can't go in that direction. Try a different way.\n" + GameInteractions.RESET);
                continue;
            }
            currentRoom = getCurrentRoom(direction,room);

        }
    }

    private static void pickUp(Map<String, Location> locationMap, String locationName, String itemName) {
        Location location = locationMap.get(locationName);
        if (location != null){
            List<Item> items = location.getItems();
            if(items.contains(itemName)){
                items.remove(itemName);
            }
        }
    }

    //
    //public void help
    //public void quit
    //public void look
    //public void go
    //master method w/switch case

    private static String getCurrentRoom(String direction, Location location){
        String result = "";
        switch (direction){
            case "west":
                result = location.getWest();
                break;
            case "east":
                result = location.getEast();
                break;
            case "south":
                result = location.getSouth();
                break;
            case "north":
                result = location.getNorth();
                break;
        }


        return result;
    }

}