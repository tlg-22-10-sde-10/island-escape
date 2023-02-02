package Utilities;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import entities.Item;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class LocationParser {
    private static JsonNode rooms;

    public static void Run() {
        Scanner sc = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();

        try {
            rooms = mapper.readTree(new File("src/main/resources/game-info.json"));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String currentRoom = "Beach";
        boolean gameRun = true;

        while (gameRun) {
            JsonNode room = rooms.get(currentRoom);
            //JsonNode items = rooms.get(currentRoom).get(currentItems);

            System.out.println(room.get("description").asText());

            if(room.has("item")) {
                for(JsonNode item : room.get("item")){
                    System.out.println(item.get("name").asText());

                }
            }

            System.out.print("Which direction would you like to go? (north, south, east, west): ");
            System.out.println("What would you like to do? go [direction] or pickup [item]");
            String direction = sc.nextLine();

            if (!room.has(direction)) {
                System.out.println("You can't go in that direction.");
                continue;
            }
                currentRoom = room.get(direction).asText();


        }
    }
}
