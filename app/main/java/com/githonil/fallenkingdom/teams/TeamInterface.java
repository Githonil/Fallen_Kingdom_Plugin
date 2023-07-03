package com.githonil.fallenkingdom;

import java.util.UUID;

/**
 * This interface can be used for communicating with a team.
 */
public interface TeamInterface extends Iterable<UUID> {

    /**
     * This method returns the team's name.
     * 
     * @return Return the team's name.
     */
    String getName();



    /**
     * This method returns the team's color.
     * 
     * @return Return the team's color.
     */
    String getColor();



    /**
     * This method adds a teammate to the team.
     * 
     * @param teammate The teammate's UUID of the team.
     */
    void addTeammate(UUID teammate);



    /**
     * This method removes a teammate to the team.
     * 
     * @param teammate The teammate's UUID of the team.
     */
    void removeTeammate(UUID teammate);

}