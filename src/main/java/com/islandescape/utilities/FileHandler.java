package com.islandescape.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
    private static final String FILE = "src/main/resources/game-info.json";

    public static void CreateGameEnviroment(){
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            File original = new File(FILE);
            File copied = new File("src/main/resources/game-info1.json");

            inputStream = new FileInputStream(original);
            outputStream = new FileOutputStream(copied);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0){
                outputStream.write(buffer,0,length);
            }


        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public static void endGameEnviroment(){
        File file = new File("src/main/resources/game-info1.json");

        if(file.delete()){
            System.out.println();;
        }
        else {
            System.out.println("fail");
        }
    }
    //

}
