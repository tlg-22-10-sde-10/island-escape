package com.islandescape.utilities;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


public class SoundEffects {

    public static Clip currentClip;

    public static void musicPlayer(String currentRoom) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        switch(currentRoom) {
            case "Beach":
                if(currentClip != null) {
                    currentClip.stop();
                }
                currentClip = openAudioClip("ocean_sounds.wav");
                break;
            case "Jungle":
                if(currentClip != null) {
                    currentClip.stop();
                }
                currentClip = openAudioClip("new_jungle_sounds.wav");
                break;
            case "Village":
                if(currentClip != null) {
                    currentClip.stop();
                }
                currentClip = openAudioClip("villages_sounds.wav");
                break;
            case "Abandoned Research Facility":
                if(currentClip != null) {
                    currentClip.stop();
                }
                currentClip = openAudioClip("research_sounds.wav");
                break;
            case "Mountain Peak":
                if(currentClip != null) {
                    currentClip.stop();
                }
                currentClip = openAudioClip("mountain_sounds.wav");
                break;
        }

        currentClip.start();
    }

    public static void itemSounds() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        openAudioClip("item_received.wav").start();
    }

    public static void catRoar() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        openAudioClip("cat_roar.wav").start();
    }

    public static void boatEscape() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        openAudioClip("boat_soundsnew.wav").start();
    }


    public static void flareGunEscape() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        openAudioClip("helicopter_escape.wav").start();
    }

    public static void predatorFish() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        openAudioClip("predator_snacking.wav").start();
    }

    public static void predatorEatsYou() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        openAudioClip("predator_deathnew.wav").start();
    }

    private static Clip openAudioClip(String clipname) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        InputStream inputStream = SoundEffects.class.getClassLoader().getResourceAsStream(clipname);
        //noinspection ConstantConditions
        InputStream buffer = new BufferedInputStream(inputStream);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(buffer);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }




}









