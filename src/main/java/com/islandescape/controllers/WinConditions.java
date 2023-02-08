package com.islandescape.controllers;

import com.islandescape.entities.Item;
import com.islandescape.entities.Location;

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
            System.out.print("You have obtained all items needed to build a boat and escape.\n Would you like to build a boat now? Yes/No: ");
            String response = input.nextLine();

            if(response.equalsIgnoreCase("yes")) {
                System.out.println("The Villagers have sympathy for you and assist you with building the boat.\n" +
                        "You win!!!! Safe travels home!!!\n");
                return true;
            } else {
                System.out.println("You have decided to not build a boat at this time. Continue to watch the time. You need to escape!");
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
            System.out.println("You are at the top of mountain");
            System.out.println("Would like to fire the flare-gun yes or no:");
            String resp = input.nextLine();

            if(resp.toLowerCase().equals("yes")){
                System.out.println("\nA rescue helicopter saw your flare it on it way");
                System.out.println("\nYOU HAVE BEEN RESCUED");
                return true;
            }
            else {
                return false;
            }
        }


        return false;

    }

}

