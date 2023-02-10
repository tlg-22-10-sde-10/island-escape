package com.islandescape.controllers;

import com.islandescape.entities.Item;
import com.islandescape.entities.Location;
import com.islandescape.utilities.AsciiArt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WinConditions {

    public static boolean playerCanBuildABoat(List<Item> itemList) {
        Scanner input = new Scanner(System.in);
        String[] boat = {"paddle", "wood-planks-pile", "rope"};
        List<String> items = new ArrayList<>();

        for(Item item: itemList){
            items.add(item.getName());
        }

        if(items.containsAll(Arrays.asList(boat))){
            System.out.print(AsciiArt.CYAN + "\nYou have obtained all items needed to build a boat and escape.\nWould you like to build a boat now? Yes/No: " + AsciiArt.RESET);
            String response = input.nextLine();

            if(response.equalsIgnoreCase("yes")) {
                System.out.println(AsciiArt.MAGENTA + "\nThe Villagers have sympathy for you and assist you with building the boat.\n" +
                        "YOU WIN!!!! Safe travels home!!!\n" + AsciiArt.RESET);
                return true;
            } else {
                System.out.println(AsciiArt.CYAN + "\nYou have decided not to build a boat at this time. Continue to watch the time. You need to escape!" + AsciiArt.RESET);
                return false;
            }
        }
        return false;
    }

    public static boolean flareWin(List<Item> items, Location location){
        Scanner input = new Scanner(System.in);
        boolean hasFlare = false;

        for(Item item : items){
            if(item.getName().equals("flare-gun")){
                hasFlare = true;
                break;
            }
        }

        if(hasFlare && location.getName().equals("Mountain Peak")){
            System.out.print(AsciiArt.CYAN +"\nYou made it to the top of mountain! Would like to fire the flare-gun to signal for help? (yes/no): " + AsciiArt.RESET);
            String resp = input.nextLine();

            if(resp.toLowerCase().equals("yes")){
                System.out.println(AsciiArt.MAGENTA + "\nA rescue helicopter saw your flare and is on the way - You have been rescued!");
                System.out.println("\nYOU WIN!");
                return true;
            }
            else {
                return false;
            }
        }


        return false;

    }

}

