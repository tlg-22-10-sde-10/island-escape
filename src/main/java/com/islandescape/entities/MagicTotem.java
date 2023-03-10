package com.islandescape.entities;

import com.islandescape.utilities.AsciiArt;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MagicTotem {

    private static final String riddle = "If you can answer the riddle, you will prove yourself worthy to enter the village >>>>\n" + AsciiArt.CYAN +
            "Riddle: I go all around the world, but never leave the corner. What am I? " + AsciiArt.RESET;
    static boolean totemEncounterPassed;

    public static boolean riddle() throws InterruptedException {
        //if(!totemEncounterPassed) {
            Scanner userAnswer = new Scanner(System.in);
            boolean answer;


            char[] array = riddle.toCharArray();
            for (char a : array) {
                System.out.print(a);
                TimeUnit.MILLISECONDS.sleep(40);
            }

            String correctAnswer = userAnswer.nextLine().toLowerCase();

            if (correctAnswer.equals("stamp")) {
                System.out.println(AsciiArt.MAGENTA + "\nThat is correct - you may enter. Welcome to the Village!\n" + AsciiArt.RESET);
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                answer = true;
               // totemEncounterPassed = true;
            } else {
                System.out.println(AsciiArt.RED + "\nThat is incorrect - you are forbidden from entering here. Be gone!\n" + AsciiArt.RESET);
                System.out.println("-----------------------------------------------------------------------------------------------------------");
                answer = false;
            }
            return answer;
        }
        //return false;
    }
//    public static boolean totemPassed(){
//        if (totemEncounterPassed){
//            System.out.println();
//        }
//        return totemEncounterPassed;
//    }
//
//    public static void setTotemEncounterPassed(boolean totemEncounterPassed) {
//        MagicTotem.totemEncounterPassed = totemEncounterPassed;
//    }

