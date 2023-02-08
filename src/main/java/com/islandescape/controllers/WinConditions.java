package com.islandescape.controllers;

import com.islandescape.entities.Item;

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
}

