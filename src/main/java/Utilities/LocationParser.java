package Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class LocationParser {

    public static void main(String[] args) throws IOException {

        //Reads the file input JSON
        File file = new File("gameinfo.json");

//        //read json file data to a string
//        byte[] gameInfoJSON = Files.readAllBytes(Paths.get("gameinfo.json"));
//
//        System.out.println(gameInfoJSON);

        //create object mapper instance
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(file, Item.class)
        //Deserialize JSON to object


        //  String JSON = objectMapper.readValue(new File("src/test/resources/json_car.json"), Car.class);

    }
}
