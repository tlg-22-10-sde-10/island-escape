//package com.islandescape.utilities;
//
//import javax.sound.sampled.*;
//import java.io.File;
//import java.io.IOException;
//
//public class SoundEffects {
//    public static Thread musicThread;
//    public static Thread oceanThread;
//    public static Thread jungleThread;
//
//    public static void musicPlayer(String currentRoom) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        File oceanFile = new File("src/main/resources/ocean_sounds.wav");
//        AudioInputStream oceanStream = AudioSystem.getAudioInputStream(oceanFile);
//        Clip oceanClip = AudioSystem.getClip();
//        oceanClip.open(oceanStream);
//
//        File jungleFile = new File("src/main/resources/jungle_sounds.wav");
//        AudioInputStream jungleStream = AudioSystem.getAudioInputStream(jungleFile);
//        Clip jungleClip = AudioSystem.getClip();
//        jungleClip.open(jungleStream);
//
////        oceanThread = new Thread(() -> {
////            oceanClip.start();
////        });
////
////        jungleThread = new Thread(() -> {
////            jungleClip.start();
////        });
////
////
////        switch (currentRoom) {
////            case ("Beach"):
////                oceanThread.start();
////                break;
////            case ("Jungle"):
////                oceanStream.close();
////                oceanThread.interrupt();
////                oceanThread.start();
////                break;
////        }
//
//            musicThread = new Thread(() -> {
//                    while (true) {
//                        switch (currentRoom) {
//                            case ("Beach"):
//                                try {
//                                    oceanClip.loop(Clip.LOOP_CONTINUOUSLY);
//                                    oceanClip.start();
//                                    jungleClip.stop();
//                                    stopMusic();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                break;
//                            case ("Jungle"):
//                                try {
//                                    jungleClip.loop(Clip.LOOP_CONTINUOUSLY);
//                                    jungleClip.start();
//                                    oceanClip.stop();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                break;
//                        }
//                    }
//                });
//                musicThread.start();
//            }
//
//            public static void stopMusic() {
//                if (musicThread != null) {
//                    musicThread.interrupt();
//                }
//            }
//
//        }
//
//    }
//}




