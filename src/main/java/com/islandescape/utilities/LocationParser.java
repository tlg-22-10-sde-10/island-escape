package com.islandescape.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.islandescape.controllers.WinConditions;
import com.islandescape.entities.Item;
import com.islandescape.entities.Location;
import com.islandescape.utilities.AsciiArt;
import com.islandescape.controllers.GameMessages;
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
    private static Map<String, Location> locationMap;
    private static String currentRoom = "Beach";
    private static Location room;
    private static boolean gameRun = true;

    String[] itemList = new String[1];
    private static List<Item> items = new ArrayList<>();


    public static void Run() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        CountdownTimer.startTimer(5);

        try (InputStream input = LocationParser.class.getClassLoader().getResourceAsStream("locations.json")) {
            List<Location> locations = mapper.readValue(input, new TypeReference<List<Location>>() {
            });
            locationMap = locations
                    .stream()
                    .collect(Collectors.toMap(Location::getName, Function.identity()));
        }
        Map<String, Location> map = locationMap;

        while (gameRun) {
            String prevRoom = currentRoom;
            room = map.get(currentRoom);

            System.out.println("Remaining Time: " + AsciiArt.MAGENTA + CountdownTimer.getTimeRemaining() + "\n" + AsciiArt.RESET);
            System.out.println(AsciiArt.CYAN + AsciiArt.underline + AsciiArt.bold + "Current Location: " + room.getName() + AsciiArt.unBold + AsciiArt.RESET);
            System.out.println(room.getDescription());

            if (WinConditions.playerCanBuildABoat(items)) {
                gameRun = false;
                break;
            }

            if (WinConditions.flareWin(items, room)) {
                gameRun = false;
                break;
            }

            if (CountdownTimer.countdownFinished()) {
                gameRun = false;
                System.out.println(AsciiArt.MAGENTA + "\nThe ground begins to shake and the Volcano on the island begins to violently erupt." + AsciiArt.RESET);
                System.out.println(AsciiArt.MAGENTA + "There is no escaping this...Your soul now belongs to the island forever - YOU LOSE!" + AsciiArt.RESET);
                System.out.println(AsciiArt.volcano);
                break;
            }

            if (room.getItems() != null) {
                System.out.println(AsciiArt.CYAN + "\nItems at this location >>>" + AsciiArt.RESET);
                for (Item item : room.getItems()) {
                    System.out.println("=> " + item.getName());
                }
            } else {
                System.out.println(AsciiArt.CYAN + "[There are no items at this location]" + AsciiArt.RESET);
            }

            System.out.print("\nWhich direction would you like to go? [Hint - You can type 'help' at any time to view a list of commands]: ");

            String action = sc.nextLine();
            System.out.println("-----------------------------------------------------------------------------------------------------------");


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
                System.out.println(AsciiArt.RED + "\nAre you sure you want to quit? Yes or No?" + AsciiArt.RESET);
                action = sc.nextLine().toLowerCase();
                if (action.equals("no")) {
                    continue;
                } else {
                    gameRun = false;
                    System.out.println(GameMessages.quitMessage());
                    continue;
                }
            }

            if (action.contains("help")) {
                System.out.println("\n-----------------------------------------------------------------------------------------------------------");
                System.out.println(AsciiArt.underline + "Here are the available commands: " + AsciiArt.RESET);
                System.out.println("-Type" + AsciiArt.CYAN + AsciiArt.bold + " 'go' (direction) to go to another location" + AsciiArt.unBold + " => Example: go north" + AsciiArt.RESET);
                System.out.println("-Type" + AsciiArt.CYAN + AsciiArt.bold + " 'pickup' (item) to place an item in your inventory" + AsciiArt.unBold + " => Example: pickup flare gun" + AsciiArt.RESET);
                System.out.println("-Type" + AsciiArt.CYAN + AsciiArt.bold + " 'show' (item) to see a description of an item" + AsciiArt.unBold + " => Example: show flare gun" + AsciiArt.RESET);
                System.out.println("-Type" + AsciiArt.CYAN + AsciiArt.bold + " 'quit'" + AsciiArt.unBold + " to end the game at any time" + AsciiArt.RESET);
                System.out.println("-----------------------------------------------------------------------------------------------------------");
            }

            //TODO: Bug keeps outprinting not a complete response
            if (!action.contains("go")) {
                System.out.println();
                continue;
            }

            String[] word = action.split(" ");
            String direction = word[1];

//            if (!locationMap.containsKey(direction)) {
//                System.out.println("Invalid location. Please try again.");
//                currentRoom = currentRoom;
//                continue;
//            }

             if (getCurrentRoom(direction, room) == null) {
                System.out.println(AsciiArt.RED + "You can't go in that direction. Try a different way.\n" + AsciiArt.RESET);
                continue;
            }


            else if (currentRoom.equals("Jungle") && direction.equals("west")) {
                System.out.println(AsciiArt.magicTotem);
                System.out.println("\nBehold! I am the protector of the village...named the Sacred Totem");
                if (!MagicTotem.riddle()) {
                    currentRoom = "Jungle";
                    continue;
                } else {
                    currentRoom = getCurrentRoom(direction, room);
                }
            }else {
                currentRoom = getCurrentRoom(direction, room);
             }
           // currentRoom = getCurrentRoom(direction, room);
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
        String result = null;
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
