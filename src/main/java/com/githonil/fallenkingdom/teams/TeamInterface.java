package com.githonil.fallenkingdom.teams;

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
     * This method returns the team's leader.
     * 
     * @return Return the team's leader.
     */
    UUID getLeader();



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



    /**
     * This method checks if a teammate is in the team.
     * 
     * @param teammate The teammate's UUID.
     * @return Return true if the teammate is in the team, else false.
     */
    boolean containTeammate(UUID teammate);

}