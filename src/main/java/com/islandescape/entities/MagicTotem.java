package com.islandescape.entities;

import com.islandescape.controllers.GameInteractions;
import com.islandescape.utilities.LocationParser;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MagicTotem {

    private static final String riddle = "If you can answer the riddle, you will prove yourself worthy to enter the village >>>>\n" + GameInteractions.MAGENTA +
            "Riddle: I go all around the world, but never leave the corner. What am I? " + GameInteractions.RESET;

    public static boolean riddle() throws InterruptedException {
        Scanner userAnswer = new Scanner(System.in);
        boolean answer;


        char[] array = riddle.toCharArray();
        for (char a : array) {
            System.out.print(a);
            TimeUnit.MILLISECONDS.sleep(40);
        }

        String correctAnswer = userAnswer.nextLine().toLowerCase();

        if (correctAnswer.equals("stamp")) {
            System.out.println("\nThat is correct - you may enter. Welcome to the Village!");
            answer = true;
        } else {
            System.out.println("\nThat is incorrect - you are forbidden from entering here. Be gone!");
            answer = false;
        }
        return answer;
    }
}
