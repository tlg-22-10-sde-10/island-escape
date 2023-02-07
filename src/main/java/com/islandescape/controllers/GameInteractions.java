/*
ASCII Art code is referenced from: https://www.baeldung.com/ascii-art-in-java
 */
package com.islandescape.controllers;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameInteractions {
    private static final String gameTitle = "I" + " " + "S" + " " + "L" + " " + "A" + " " + "N" + " " + "D" +
            " " + " " + "E" + " " + "S" + " " + "C" + " " + "A" + " " + "P" + " " + "E"  ;
    private static final int width = 200;
    private static final int height = 15;
    private static final int fontSize = 12;
    private static final int xCoordinate = 10;
    private static final int yCoordinate = 12;

    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";
    public static final String MAGENTA = "\u001b[35m";

    public static void gameArt(){
        BufferedImage bufferedImage = new BufferedImage(
                width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics graphics = bufferedImage.getGraphics();
        graphics.setFont(new Font("Courier", Font.BOLD, fontSize));

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.drawString(gameTitle, xCoordinate, yCoordinate);

        for (int y = 0; y < height; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < width; x++) {
                stringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "#");
            }

            if (stringBuilder.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(CYAN + stringBuilder + RESET);
        }
    }

    public static void gameIntroductionText() throws InterruptedException {
        final String bold = "\033[1m";
        final String unBold = "\033[0m";
        final String introductionText = "You are startled awake by the sensation of water crashing on your feet. " +
                "You look around confused and \nrealize you are no longer in your warm, comfy bed " +
                "but on a strange island in the middle of nowhere.\n" +
                "\n" +
                "You reach into your pocket to see if you have your cell phone to call for help and find a note that reads:\n" +
                "\n" + MAGENTA +
                "“Hello there. I know you’re wondering where you are. That’s not very important. " +
                "\nWhat is important is how you choose to leave this place. You have two options:\n" +
                "\n1) Search the island for the materials needed to build a boat to help you sail away\n-OR-" +
                "\n2) Search for a safe that will contain a flare gun to signal help.\n " +
                "\nBeware though, each option comes with its own challenges.\n" +
                "\n" + "P.S. Timing is very important. You are only allotted a certain amount of time to escape " +
                "\nbefore a volcano erupts and destroys the island.\n " + bold +
                "\nHappy Escaping!\n" + unBold + RESET;

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
