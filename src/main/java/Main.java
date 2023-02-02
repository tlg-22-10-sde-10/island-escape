import java.util.Scanner;
import Utilities.LocationParser;
import game_state.GameState;
import entities.MagicTotem;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameState.gameArt();

        Scanner userInput = new Scanner(System.in);
        boolean continueGame = true;
        while (continueGame){
            System.out.print("Would you like to play the 'Survival: Island Escape' game? 'Yes' or 'No': ");
            String start = userInput.nextLine().toLowerCase();
            switch (start) {
                case "yes":
                    GameState.gameIntroductionText();
                    break;
                case "no":
                    System.out.println(GameState.exitMessage());
                    continueGame = false;
                    break;
                default:
                    System.out.println("Invalid input. Please type 'Yes' or 'No'.");
            }
        }
    }
}
