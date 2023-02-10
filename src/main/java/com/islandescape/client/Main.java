package com.islandescape.client;

import java.io.IOException;
import java.util.Scanner;

import com.islandescape.utilities.AsciiArt;
import com.islandescape.utilities.LocationParser;
import com.islandescape.controllers.GameMessages;
//import com.islandescape.utilities.SoundEffects;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {

        System.out.println();
        System.out.println(AsciiArt.gameTitleArt);

        Scanner userInput = new Scanner(System.in);
        boolean continueGame = true;
        while (continueGame) {
            System.out.print("Welcome to Survival: Island Escape. Start Game? 'Yes' or 'No': ");
            String start = userInput.nextLine().toLowerCase();
            switch (start) {
                case "yes":
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    GameMessages.gameIntroductionText();
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    LocationParser.Run();
                    continueGame = false;
                    break;
                case "no":
                    System.out.println(GameMessages.exitMessage());
                    continueGame = false;
                    break;
                default:
                    System.out.println("Invalid input. Please type 'Yes' or 'No'.");
            }
        }

    }
}
