package com.githonil.fallenkingdom.timers;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Objects;

/**
 * This class represents a Fallen Kingdom's timer.
 */
public class FallenKingdomTimer extends TimerTask implements FallenkingdomTimerInteface {
    
    /**
     * This attribute represents the timer's seconds.
     */
    private int seconds;



    /**
     * This attribute represents the timer's minutes.
     */
    private int minutes;



    /**
     * This attribute represents the timer's days.
     */
    private int days;



    /**
     * This attribute represents the timer's core.
     */
    private Timer timerCore;



    /**
     * This attribute represents if the timer runs.
     */
    private boolean running;



    /**
     * The timer's constructor.
     */
    public FallenKingdomTimer() {
        this.seconds = 0;
        this.minutes = 0;
        this.days = 0;
        this.timerCore = new Timer();
        this.running = false;
    }



    /**
     * This method runs the timer's core.
     */
    @Override
    public void run() {
        if (!running) return;

        this.seconds++;
        if (this.seconds >= 60) {
            this.seconds = 0;
            this.minutes++;
        }
        else if (this.minutes >= 20) {
            this.minutes = 0;
            this.days++;
        }
    }



    /**
     * This method starts the timer.
     */
    @Override
    public void start() {
        running = true;
        timerCore.schedule(this, 0, 1000);
    }



    /**
     * This method stops the timer.
     */
    @Override
    public void stop() {
        running = false;
    }



    /**
     * This method resets the timer.
     */
    @Override
    public void reset() {
        seconds = 0;
        minutes = 0;
        days = 0;
        running = 0;
        timerCore.cancel();
    }



    /**
     * This method returns the timer's measures in seconds.
     * 
     * @return Return the timer's measures in seconds.
     */
    @Override
    public int getSeconds() {
        return seconds;
    }



    /**
     * This method returns the timer's measures in minutes.
     * 
     * @return Return the timer's measures in minutes.
     */
    @Override
    public int getMinutes() {
        return minutes;
    }



    /**
     * This method returns the timer's measures in days.
     * 
     * @return Return the timer's measures in days.
     */
    @Override
    public int getDays() {
        return days;
    }



    /**
     * This method converts the timer to string.
     * 
     * @return Return the timer converts to string.
     */
    @Override
    public String toString() {
        return "Days : " + days + "\nMinutes : " + minutes + "\nSeconds : " + seconds;
    }



    /**
     * This methods compares the timer with an object.
     * 
     * @param o The object to compare.
     * @return Return true if the timer is equal to the object, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FallenKingdomTimer)) return false;

        FallenKingdomTimer oTimer = (FallenKingdomTimer) o;
        return oTimer.seconds == this.seconds && oTimer.minutes == this.minutes && oTimer.days == this.days;
    }



    /**
     * This method returns the timer's hash code.
     * 
     * @return Return the timer's hash code.
     */
    public int hashCode() {
        Objects.hash(seconds, minutes, days);
    }

}