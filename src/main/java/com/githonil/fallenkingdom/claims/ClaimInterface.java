package com.githonil.fallenkingdom.claims;

import java.util.UUID;
import java.io.Serializable;

/**
 * This interface can be used for communicating with a claim.
 */
public interface ClaimInterface extends Serializable {

    /**
     * This method returns the claim's coordinate.
     * 
     * @return Return the claim's coordinate.
     */
    int[] getCoordinate();



    /**
     * This method checks if a player is authorized at the claim.
     * 
     * @param player The player's UUID.
     * @return Return true if the player is authorized, else false.
     */
    boolean checkPlayer(UUID player);

}