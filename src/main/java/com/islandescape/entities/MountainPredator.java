package com.islandescape.entities;

import com.islandescape.controllers.GameMessages;
import com.islandescape.utilities.AsciiArt;
import com.islandescape.utilities.SoundEffects;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class MountainPredator {
    static boolean catEncounter = false;
    static boolean encounterWithoutFish;
    static boolean predatorDeath;

    public static boolean PredatorAttack(String currentRoom, String direction, List<Item> itemList) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {

        boolean attackResult = true;

        if (currentRoom.equals("Jungle") && direction.equals("east")) {

            if (catEncounter == false) {

                SoundEffects.catRoar();
                System.out.println(AsciiArt.predator);
                System.out.println(AsciiArt.MAGENTA + "\nAs you move throughout the Jungle, you get an uneasy feeling that something is watching you...");
                System.out.println("Suddenly, A furry predator jumps out of the jungle and begins to stare...as if you were it's next meal!");
                System.out.println("You've got to make a decision...Your life depends on it!" + AsciiArt.RESET);

                while (attackResult) {
                    String fish = "fish";
                    List<String> items = new ArrayList<>();

                    for (Item item : itemList) {
                        items.add(item.getName());
                    }

                    if (!items.contains(fish)){
                        encounterWithoutFish = true;
                        break;
                    }


                    if (items.contains(fish)) {
                        System.out.print("\nBig cat predators love fish, would you like to throw it to the animal? Yes/No: ");
                        Scanner input = new Scanner(System.in);
                        String nextAction = input.next().toLowerCase();
                        if (nextAction.equals("yes")) {
                            System.out.println(AsciiArt.CYAN+"\nYou throw the fish on the ground, it now seems friendly and will not attack anymore!"+AsciiArt.RESET);
                            System.out.println("-----------------------------------------------------------------------------------------------------------");
                            SoundEffects.predatorFish();
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


    public static boolean PredatorDeath() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {

        if (predatorDeath) {
            SoundEffects.predatorEatsYou();
            System.out.println(AsciiArt.RED+"\nThe predator decides to take the fish from you...along with your life!"+AsciiArt.RESET);
            GameMessages.exitMessage();
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        return predatorDeath;
    }


    public static boolean EncounterWithoutFish() {
        if (encounterWithoutFish) {
            System.out.println(AsciiArt.CYAN+"\nYou need something to distract this predator...it's safer to turn back for now"+AsciiArt.RESET);
        }
        return encounterWithoutFish;
    }


    public static void setEncounterWithoutFish(boolean encounterWithoutFish) {
        MountainPredator.encounterWithoutFish = encounterWithoutFish;
    }


}