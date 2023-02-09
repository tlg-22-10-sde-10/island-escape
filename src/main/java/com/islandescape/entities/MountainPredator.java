package com.islandescape.entities;

import com.islandescape.controllers.GameMessages;
import com.islandescape.utilities.AsciiArt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MountainPredator {
    static boolean catEncounter = false;
    static boolean encounterWithoutFish;
    static boolean predatorDeath;

    public static boolean PredatorAttack(String currentRoom, String direction, List<Item> itemList) {

        boolean attackResult = true;

        if (currentRoom.equals("Jungle") && direction.equals("east")) {

            if (catEncounter == false) {

                //Play Predator Sound effect()
                System.out.println(AsciiArt.predator);
                System.out.println("As you move up the Mountain, you get an uneasy feeling that something is watching you...");
                System.out.println("Suddenly, A furry predator jumps out of the jungle and begins to stare...as if you were it's next meal!");
                System.out.println("You've got to make a decision...Your life depends on it!");

                while (attackResult) {
                    String fish = "Fish";
                    List<String> items = new ArrayList<>();

                    for (Item item : itemList) {
                        items.add(item.getName());
                    }

                    if (itemList.contains(fish)){
                        encounterWithoutFish = true;
                    }


                    if (items.contains(fish)) {
                        System.out.println("Big cat predators love fish, would you like to throw it to the animal? Yes/No: ");
                        Scanner input = new Scanner(System.in);
                        String nextAction = input.next().toLowerCase();
                        if (nextAction.equals("yes")) {
                            System.out.println("You throw the fish on the ground, it now seems friendly and will not attack anymore!");
                            catEncounter = true;
                            attackResult = false;
                        } else if (nextAction.equals("no")) {
                            predatorDeath = true;
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }


    public static boolean PredatorDeath() {

        if (predatorDeath) {
            System.out.println("The predator decides to take the fish from you...along your life!");
            GameMessages.exitMessage();
        }
        return predatorDeath;
    }


    public static boolean EncounterWithoutFish() {
        if (encounterWithoutFish) {
            System.out.println("You need something to distract this predator...it's safer to turn back for now");
        }
        return encounterWithoutFish;
    }


}