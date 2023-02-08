package com.islandescape.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.islandescape.controllers.WinConditions;
import com.islandescape.entities.Item;
import com.islandescape.entities.Location;
import com.islandescape.controllers.GameInteractions;
import com.islandescape.entities.MagicTotem;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;
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
    private static Map<String, Location> locationMap;
    private static String currentRoom = "Beach";
    private static Location room;
    private static boolean gameRun = true;

    String[] itemList = new String[1];
    private static List<Item> items = new ArrayList<>();


    public static void Run() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        CountdownTimer.startTimer(15);

        try (InputStream input = LocationParser.class.getClassLoader().getResourceAsStream("locations.json")) {
            List<Location> locations = mapper.readValue(input, new TypeReference<List<Location>>() {
            });
            locationMap = locations
                    .stream()
                    .collect(Collectors.toMap(Location::getName, Function.identity()));
        }
        Map<String, Location> map = locationMap;

        while (gameRun) {
            room = map.get(currentRoom);

            if(WinConditions.playerCanBuildABoat(items)){
                gameRun = false;
                break;
            };


            System.out.println("Current Location: " + room.getName());
            System.out.println(room.getDescription());
            System.out.println("\nRemaining Time: " + GameInteractions.MAGENTA + CountdownTimer.getTimeRemaining()+GameInteractions.RESET);
            //room.getItems().stream().forEach(x -> System.out.println(x.getName()));
            if(WinConditions.flareWin(items,room)){
                gameRun = false;
                break;
            }

            if (CountdownTimer.countdownFinished()){
                gameRun = false;
                System.out.println("You have died");
                break;
            }

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
//            LocationParser.getInfo(action);
//            LocationParser.getCurrentRoom(action, room);
//            LocationParser.help(action);


            if (action.contains("look") && room.getItems() != null) {
                for (Item item : room.getItems()) {
                    if (item.getName().toLowerCase().equals(action.substring(5))) {
                        System.out.println(item.getDescription());

                    }
                }
            }


            if (action.contains("pickup")) {
                String[] item = action.split(" ");

                pickUp(room, item[1]);
            }

            if (action.toLowerCase().equals("show backpack")) {
                showBackPack();

            }

            if (action.equals("quit")) {
                System.out.println(RED + "\nAre you sure you want to quit? Yes or No?" + GameInteractions.RESET);
                action = sc.nextLine().toLowerCase();
                if (action.equals("no")) {
                    continue;
                } else {
                    gameRun = false;
                    System.out.println(GameInteractions.quitMessage());
                    continue;
                }
            }

            if (action.contains("help")) {
                System.out.println(underline + "\nHere are the available commands: " + GameInteractions.RESET);
                System.out.println("-Type" + GameInteractions.CYAN + bold + " 'go' (direction)" + unBold + " => Example: go north" + GameInteractions.RESET);
                System.out.println("-Type" + GameInteractions.CYAN + bold + " 'pickup' (item)" + unBold + " => Example: pickup flare gun" + GameInteractions.RESET);
                System.out.println("-Type" + GameInteractions.CYAN + bold + " 'quit'" + unBold + " => Quits Game\n" + GameInteractions.RESET);
            }

            //TODO: Bug keeps outprinting not a complete response
            if (!action.contains("go")) {
                //System.out.println("That's not a complete response. Please try again.");
                System.out.println();
                continue;
            }

            String[] word = action.split(" ");
            String direction = word[1];

            if (getCurrentRoom(direction, room) == null) {
                System.out.println(RED + "You can't go in that direction. Try a different way.\n" + GameInteractions.RESET);
            }

            if (currentRoom.equals("Jungle") && direction.equals("west")) {
                System.out.println("Behold! I am the protector of the village...named the Sacred Totem");
                if (!MagicTotem.riddle()) {
                    currentRoom = "Jungle";
                    continue;
                } else {
                    currentRoom = getCurrentRoom(direction, room);
                }
            }
            currentRoom = getCurrentRoom(direction, room);
        }
    }


    private static void pickUp(Location room, String itemName) {
        Optional<Item> itemToPickUp = room.getItems().stream()
                .filter(item -> item.getName().toLowerCase().equals(itemName.toLowerCase()))
                .findFirst();
        if (itemToPickUp.isPresent()) {
            Item item = itemToPickUp.get();
            items.add(item);
            room.getItems().remove(item);
            System.out.println("You have picked up the following item: " + item.getName());
        } else {
            System.out.println("Item not found");
        }

    }

    private static void showBackPack() {
        items.stream().forEach(item -> System.out.println(item.getName()));
    }


//    private static void help(String action) {
//        if (action.contains("help")) {
//            System.out.println(underline + "\nHere are the available commands: " + GameInteractions.RESET);
//            System.out.println("-Type" + GameInteractions.CYAN + bold + " 'go' (direction)" + unBold + " => Example: go north" + GameInteractions.RESET);
//            System.out.println("-Type" + GameInteractions.CYAN + bold + " 'pickup' (item)" + unBold + " => Example: pickup flare gun" + GameInteractions.RESET);
//            System.out.println("-Type" + GameInteractions.CYAN + bold + " 'quit'" + unBold + " => Quits Game\n" + GameInteractions.RESET);
//        }
//    }

// TODO: Make everything into methods
//    private static void look(String action) {
//        if (action.contains("look") && room.getItems() != null) {
//            for (Item item : room.getItems()) {
//                if (item.getName().toLowerCase().equals(action.substring(5))) {
//                    System.out.println(item.getDescription());
//                }
//            }
//        }
//    }
//
//    private static void quit(String action) {
//        if (action.equals("quit")) {
//            System.out.println(RED + "\nAre you sure you want to quit? Okay or No?" + GameInteractions.RESET);
//            Scanner scanner = new Scanner(System.in);
//            String nextAction = scanner.next();
//            if (nextAction.equals("Okay")) {
//                System.out.println(GameInteractions.quitMessage());
//                gameRun = false;
//            } else {
//                gameRun = true;
//            }
//        }
//    }


//    private static String getInfo(String action) {
//        String actionInput = "";
//        switch (action) {
//            case "help":
//                LocationParser.help(action);
//                break;
//            default:
//                System.out.println("wut from getinfo case");
//        }
//        return actionInput;
//    }

    private static String getCurrentRoom(String direction, Location location) {
        String result = "";
        switch (direction) {
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
            default:
                System.out.println("That is an invalid input from current room case");
        }
        return result;
    }
}
