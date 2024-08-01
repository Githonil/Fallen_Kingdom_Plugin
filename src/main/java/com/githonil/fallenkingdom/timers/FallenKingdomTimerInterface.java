package com.githonil.fallenkingdom.timers;

import java.io.Serializable;

/**
 * This interface can be used for communicating with the Fallen Kingdom's timer.
 */
public interface FallenKingdomTimerInterface extends Serializable {

    /**
     * This method starts the timer.
     */
    void start();



    /**
     * This method stops the timer.
     */
    void stop();



    /**
     * This method resets the timer.
     */
    void reset();



    /**
     * This method returns the timer's measures in seconds.
     * 
     * @return Return the timer's measures in seconds.
     */
    int getSeconds();



    /**
     * This method returns the timer's measures in minutes.
     * 
     * @return Return the timer's measures in minutes.
     */
    int getMinutes();



    /**
     * This method returns the timer's measures in days.
     * 
     * @return Return the timer's measures in days.
     */
    int getDays();

}