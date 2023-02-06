import java.util.Scanner;
import utilities.LocationParser;
import game_state.GameState;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        GameState.gameArt();
        System.out.println();

        Scanner userInput = new Scanner(System.in);
        boolean continueGame = true;
        while (continueGame){
            System.out.print("Welcome to Survival: Island Escape. Start Game? 'Yes' or 'No': ");
            String start = userInput.nextLine().toLowerCase();
            switch (start) {
                case "yes":
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    GameState.gameIntroductionText();
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    LocationParser.Run();
                    break;
                case "no":
                    System.out.println(GameState.exitMessage());
                    continueGame = false;
                    break;
                default:
                    System.out.println("I don't recognize your response. Please type 'Yes' or 'No'.");
            }
        }

    }
}
