package com.islandescape.utilities;

import java.util.concurrent.TimeUnit;

public class CountdownTimer {
    private static long startTime;
    private static long endTime;
    private static boolean hasFinished = false;

    public static void startTimer(int minutes) {
        int seconds = 0;
        startTime = System.currentTimeMillis();
        endTime = startTime + TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.SECONDS.toMillis(seconds);
    }

    public static String getTimeRemaining() {
        long remainingTime = endTime - System.currentTimeMillis();
        long minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(remainingTime);
        long secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(remainingTime) - TimeUnit.MINUTES.toSeconds(minutesRemaining);
        if(remainingTime<=0){
            hasFinished = true;
        }

        return String.format("%02d:%02d", minutesRemaining, secondsRemaining);
    }


    public static boolean countdownFinished(){
        return hasFinished;
    }

}













