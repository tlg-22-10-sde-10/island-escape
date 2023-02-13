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
    public static Clip itemClip;
    public static Clip catRoarClip;
    public static Clip boatEscapeClip;
    public static Clip heliClip;
    public static Clip predSnackClip;
    public static Clip predEatsYouClip;


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
            case("mute"):
                currentClip.stop();
        }

        currentClip.start();
    }



    public static void itemSounds() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File itemReceivedFile = new File("src/main/resources/item_received.wav");
        AudioInputStream itemStream = AudioSystem.getAudioInputStream(itemReceivedFile);
        itemClip = AudioSystem.getClip();
        itemClip.open(itemStream);

        itemClip.start();

    }


    public static void catRoar() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File catFile = new File("src/main/resources/cat_roar.wav");
        AudioInputStream catStream = AudioSystem.getAudioInputStream(catFile);
        catRoarClip = AudioSystem.getClip();
        catRoarClip.open(catStream);

        catRoarClip.start();

    }

    public static void boatEscape() throws UnsupportedAudioFileException, IOException, LineUnavailableException{

        File boatFile = new File("src/main/resources/boat_soundsnew.wav");
        AudioInputStream boatStream = AudioSystem.getAudioInputStream(boatFile);
        boatEscapeClip = AudioSystem.getClip();
        boatEscapeClip.open(boatStream);

        boatEscapeClip.start();

    }


    public static void flareGunEscape() throws UnsupportedAudioFileException, IOException, LineUnavailableException{

        File heliFile = new File("src/main/resources/helicopter_escape.wav");
        AudioInputStream heliStream = AudioSystem.getAudioInputStream(heliFile);
        heliClip = AudioSystem.getClip();
        heliClip.open(heliStream);

        heliClip.start();

    }

    public static void predatorFish() throws UnsupportedAudioFileException, IOException, LineUnavailableException{

        File predSnackFile = new File("src/main/resources/predator_snacking.wav");
        AudioInputStream predSnackStream = AudioSystem.getAudioInputStream(predSnackFile);
        predSnackClip = AudioSystem.getClip();
        predSnackClip.open(predSnackStream);

        predSnackClip.start();

    }

    public static void predatorEatsYou() throws UnsupportedAudioFileException, IOException, LineUnavailableException{

        File predEatsYou = new File("src/main/resources/predator_deathnew.wav");
        AudioInputStream predEatsYouStream = AudioSystem.getAudioInputStream(predEatsYou);
        predEatsYouClip = AudioSystem.getClip();
        predEatsYouClip.open(predEatsYouStream);

        predEatsYouClip.start();

    }






}









