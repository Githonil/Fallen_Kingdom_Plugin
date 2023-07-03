package com.githonil.fallenkingdom.claims;

import com.githonil.fallenkingdom.teams;

import java.util.UUID;
import java.util.Objects;

/**
 * This class represents a claim.
 */
public class Claim implements ClaimInterface {
    
    /**
     * This attribute represents who is authorized.
     */
    private TeamInterface team;



    /**
     * This attribute represents the claim's coordinate.
     */
    private int[] coordinate;



    /**
     * The claim's constructor.
     * 
     * @param x The element X.
     * @param y The element Y.
     * @param z The element Z.
     * @param team The claim's team.
     */
    public Claim(int x, int y, int z, TeamInterface team) {
        this.coordinate = new int[3];
        this.coordinate[0] = x;
        this.coordinate[1] = y;
        this.coordinate[2] = z;
        this.team = team;
    }



    /**
     * This method returns the claim's coordinate.
     * 
     * @return Return the claim's coordinate.
     */
    @Override
    int[] getCoordinate() {
        return coordinate.copy();
    }



    /**
     * This method checks if a player is authorized at the claim.
     * 
     * @param UUID The player's UUID.
     * @return Return true if the player is authorized, else false.
     */
    @Override
    boolean checkPlayer(UUID player) {
        return team.containTeammate(player);
    }



    /**
     * This method converts the claim to string.
     * 
     * @return Return the claim converts to string.
     */
    @Override
    public toString() {
        return "Coordinate : [" + coordinate[0] + ", " + coordinate[1] + ", " + coordinate[2] + "]\n" + team;
    }



    /**
     * This method compares the claim with an object.
     * 
     * @param o The object to compare.
     * @return Return true if the claim is equal to the object, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Claim)) return false;

        Claim oClaim = (Claim) o;
        return oClaim.team.equals(this.team) && oClaim.coordinate.equals(this.coordinate);
    }



    /**
     * This method returns the claim's hashcode.
     * 
     * @return Return the claim's hashcode.
     */
    @Override
    public int hashcode() {
        return Objects.hash(this.team, this.coordinate);
    }

}