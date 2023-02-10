/*
ASCII Art code is referenced from: https://www.baeldung.com/ascii-art-in-java
 */
package com.islandescape.controllers;

import com.islandescape.utilities.AsciiArt;
import java.util.concurrent.TimeUnit;

public class GameMessages {

    public static void gameIntroductionText() throws InterruptedException {
        final String bold = "\033[1m";
        final String unBold = "\033[0m";
        final String introductionText = "You are startled awake by the sensation of water crashing on your feet. " +
                "You look around confused and \nrealize you are no longer in your warm, comfy bed " +
                "but on a strange island in the middle of nowhere.\n" +
                "\n" +
                "You reach into your pocket to see if you have your cell phone to call for help and find a note that reads:\n" +
                "\n" + AsciiArt.MAGENTA +
                "“Hello there. I know you’re wondering where you are. That’s not very important. " +
                "\nWhat is important is how you choose to leave this place. You have two options:\n" +
                "\n1) Search the island for the materials needed to build a boat to help you sail away" +
                "\n(Here's a clue - you will need the following materials: paddle, rope, wood-planks-pile)\n"+
                "\n-OR-\n" +
                "\n2) Search for a safe that will contain a flare gun to signal help.\n " +
                "\nBeware though, each option comes with its own challenges and you only have 5 minutes to escape" +
                "\nbefore a volcano erupts and destroys the island.\n" +
                "\nP.S. There will be items along the way that may prove useful later. Don't forget to pick them up!" +
                "\n(Hint - you can always type in show inventory to see what items you have in your possession)\n" + bold +
                "\nHappy Escaping!\n" + unBold + AsciiArt.RESET;

        char[] array = introductionText.toCharArray();
        for (char text : array) {
            System.out.print(text);
            TimeUnit.MILLISECONDS.sleep(0);
        }
    }

    public static String quitMessage() {
        return "The island will see you again. Goodbye!";
    }

    public static String exitMessage() {
        return "Maybe next time. Goodbye!";
    }

}
