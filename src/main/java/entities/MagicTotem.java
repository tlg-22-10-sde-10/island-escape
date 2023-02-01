package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MagicTotem {
    private String riddle;
    private String answer;

    public void getRiddle() throws IOException {

        URL url = new URL("http://riddles-api.vercel.app/random");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputline;
        while((inputline = input.readLine()) !=null){
            System.out.println(inputline);
        }
        input.close();
    }

}
