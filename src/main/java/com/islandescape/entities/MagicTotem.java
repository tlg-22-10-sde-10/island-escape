package com.islandescape.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MagicTotem {
    private static final String API = "https://riddles-api.vercel.app/random";
    private String riddle;
    private String answer;

    public String getRiddle() {
        return riddle;
    }

    public void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void riddle() throws IOException {
        try {
            String jsonString = retrieveJsonStringFromAPI();
            String[] riddleAndAnswer = extractRiddleAndAnswer(jsonString);
            setRiddle(riddleAndAnswer[0]);
            setAnswer(riddleAndAnswer[1]);

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private String retrieveJsonStringFromAPI() throws Exception {

        URL url = new URL(API);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept","application/json");

        if(connection.getResponseCode() == 200){
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        }
        else {
            throw new Exception("Failed to retrive riddle Response code: " + connection.getResponseCode() );
        }
    }


    private String[] extractRiddleAndAnswer(String jsonString) throws Exception{

        int riddleStartIndex = jsonString.indexOf("\"riddle\":") + 10;
        int riddleEndIndex = jsonString.indexOf("\",",riddleStartIndex);
        String riddle = jsonString.substring(riddleStartIndex,riddleEndIndex);

        int answerStartIndex = jsonString.indexOf("\"answer\":") + 10;
        int answerEndIndex = jsonString.indexOf("\"}",answerStartIndex);
        String answer = jsonString.substring(answerStartIndex,answerEndIndex);

        return new String[]{riddle,answer};
    }

}
