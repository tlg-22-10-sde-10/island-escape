package Utilities;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Item;

import java.io.File;
import java.io.IOException;


public class LocationParser {

    public static void main(String[] args) throws IOException {
         jsonRootParser();

    }

    private static void jsonRootParser() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("C:\\Island-Escape\\src\\main\\resources\\game-info.json");
        JsonNode jsonRoot = objectMapper.readTree(file);

        for(JsonNode node : jsonRoot){
            String allNodes = node.toString();
            System.out.println(allNodes);
        }

    }
}
