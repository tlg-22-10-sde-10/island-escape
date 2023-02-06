package com.islandescape.utilities;
import java.util.concurrent.TimeUnit;

 public class CountdownTimer {
        public static void main(String[] args) throws InterruptedException {
            int minutes = 0; //Initial minutes set
            int seconds = 5; // Change this to the number of additional seconds you want to count down from

            long startTime = System.currentTimeMillis();
            long endTime = startTime + TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.SECONDS.toMillis(seconds);

            while (System.currentTimeMillis() < endTime) {
                long remainingTime = endTime - System.currentTimeMillis();
                long minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(remainingTime);
                long secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(remainingTime) - TimeUnit.MINUTES.toSeconds(minutesRemaining);

                System.out.println(String.format("%02d:%02d", minutesRemaining, secondsRemaining));
                Thread.sleep(1000); // Wait for 1 second
            }

            System.out.println("Time's up!");//Change to the
        }
    }













