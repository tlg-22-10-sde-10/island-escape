package com.islandescape.client;

import java.io.IOException;
import java.util.Scanner;
import com.islandescape.utilities.LocationParser;
import com.islandescape.controllers.GameInteractions;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        GameInteractions.gameArt();
        System.out.println();

        Scanner userInput = new Scanner(System.in);
        boolean continueGame = true;
        while (continueGame){
            System.out.print("Welcome to Survival: Island Escape. Start Game? 'Yes' or 'No': ");
            String start = userInput.nextLine().toLowerCase();
            switch (start) {
                case "yes":
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    GameInteractions.gameIntroductionText();
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    LocationParser.Run();
                    break;
                case "no":
                    System.out.println(GameInteractions.exitMessage());
                    continueGame = false;
                    break;
                default:
                    System.out.println("Invalid input. Please type 'Yes' or 'No'.");
            }
        }

    }
}