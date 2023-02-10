package com.islandescape.utilities;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffects {
    public static Clip oceanClip;
    public static Clip jungleClip;
    public static Clip researchClip;
    public static Clip villageClip;
    public static Clip mountainClip;
    public static Clip currentClip;

    public static void musicPlayer(String currentRoom) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File oceanFile = new File("src/main/resources/ocean_sounds.wav");
        AudioInputStream oceanStream = AudioSystem.getAudioInputStream(oceanFile);
        oceanClip = AudioSystem.getClip();
        oceanClip.open(oceanStream);

        File jungleFile = new File("src/main/resources/new_jungle_sounds.wav");
        AudioInputStream jungleStream = AudioSystem.getAudioInputStream(jungleFile);
        jungleClip = AudioSystem.getClip();
        jungleClip.open(jungleStream);

        File mountainFile = new File("src/main/resources/mountain_sounds.wav");
        AudioInputStream mountainStream = AudioSystem.getAudioInputStream(mountainFile);
        mountainClip = AudioSystem.getClip();
        mountainClip.open(mountainStream);

        File researchFile = new File("src/main/resources/research_sounds.wav");
        AudioInputStream researchStream = AudioSystem.getAudioInputStream(researchFile);
        researchClip = AudioSystem.getClip();
        researchClip.open(researchStream);

        File villageFile = new File("src/main/resources/villages_sounds.wav");
        AudioInputStream villageStream = AudioSystem.getAudioInputStream(villageFile);
        villageClip = AudioSystem.getClip();
        villageClip.open(villageStream);


        switch (currentRoom) {
            case ("Beach"):
                if (currentClip != null) {
                    currentClip.stop();
                }
                currentClip = oceanClip;
                break;
            case ("Jungle"):
                if (currentClip != null) {
                    currentClip.stop();
                }
                currentClip = jungleClip;
                break;
            case("Village"):
                if (currentClip != null) {
                    currentClip.stop();
                }
                currentClip = villageClip;
                break;
            case("Abandoned Research Facility"):
                if (currentClip != null) {
                    currentClip.stop();
                }
                currentClip = researchClip;
                break;
            case("Mountain Peak"):
                if (currentClip != null) {
                    currentClip.stop();
                }
                currentClip = mountainClip;
                break;
        }
        currentClip.start();
    }
}









