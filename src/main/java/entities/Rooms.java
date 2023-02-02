package entities;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

public class Rooms {
    private static Map<String, Room> rooms;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        rooms = loadRoomsFromFile();
        String currentRoom = "Beach";
        while (true) {
            Room room = rooms.get(currentRoom);
            System.out.println(room.getDescription());
            System.out.print("Which direction would you like to go? (north, south, east, west): ");
            String direction = sc.nextLine();
            String nextRoom = moveToNextRoom(room, direction);
            if (nextRoom == null) {
                System.out.println("You can't go in that direction.");
                continue;
            }
            currentRoom = nextRoom;
        }
    }

    private static Map<String, Room> loadRoomsFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File("C:\\Island-Escape\\src\\main\\resources\\game-info.json");
            return mapper.readValue(new FileReader("game-info.json"),
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, Room.class));
        } catch (Exception e) {
           // e.getMessage();
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static String moveToNextRoom(Room room, String direction) {
        if (!room.getExits().containsKey(direction)) {
            return null;
        }
        return room.getExits().get(direction);
    }
}

class Room {
    private String description;
    private Map<String, String> exits;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getExits() {
        return exits;
    }

    public void setExits(Map<String, String> exits) {
        this.exits = exits;
    }
}
