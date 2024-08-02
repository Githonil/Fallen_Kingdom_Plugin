package com.githonil.fallenkingdom.teams;

import java.util.HashSet;
import java.util.UUID;
import java.util.Objects;
import java.util.Iterator;
import java.io.Serial;

/**
 * This class reprensents a team.
 */
public class Team implements TeamInterface {

    /**
     * This attribute represents the ID for the serializable.
     */
    @Serial
    private static final long serialVersionUID = 8410197109L;



    /**
     * This attribute represents the team's name.
     */
    private String name;



    /**
     * This attribute represents the leader's UUID of the team.
     */
    private UUID leader;



    /**
     * This attribute represents the team's teammates list.
     */
    private HashSet<UUID> teammates;



    /**
     * The team's constructor.
     * 
     * @param name The team's name.
     * @param leader The leader's UUID from the team.
     */
    public Team(String name, UUID leader) {
        this.name = name;
        this.leader = leader;
        this.teammates = new HashSet<>();
        this.addTeammate(leader);
    }



    /**
     * This method returns the team's name.
     * 
     * @return Return the team's name.
     */
    @Override
    public String getName() {
        return name;
    }



    /**
     * This method returns the team's leader.
     * 
     * @return Return the team's leader.
     */
    @Override
    public UUID getLeader() {
        return leader;
    }



    /**
     * This method adds a teammate to the team.
     * 
     * @param teammate The teammate's UUID of the team.
     */
    @Override
    public void addTeammate(UUID teammate) {
        teammates.add(teammate);
    }



    /**
     * This method removes a teammate to the team.
     * 
     * @param teammate The teammate's UUID of the team.
     */
    @Override
    public void removeTeammate(UUID teammate) {
        teammates.remove(teammate);
    }



    /**
     * This method checks if a teammate is in the team.
     * 
     * @param teammate The teammate's UUID.
     */
    @Override
    public boolean containTeammate(UUID teammate) {
        return teammates.contains(teammate);
    }



    /**
     * The method returns an iterator of the team's teammates.
     * 
     * @return Return an iterator of the team's teammates.
     */
    @Override
    public Iterator<UUID> iterator() {
        return teammates.iterator();
    }



    /**
     * This method reload the team if attribut is transient.
     */
    public void reloaderTeam() {}



    /**
     * This method reload a teammate if attribut is transient.
     * 
     * @param teammate The teammate to reload.
     */
    public void reloaderTeammate(UUID teammate) {}



    /**
     * This method converts the team to string.
     * 
     * @return Return the team converts to string.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(32);
        str.append("Name : " + name + "\nPlayers's UUID : [");

        for(UUID teammate : teammates) {
            str.append(teammate + ", ");
        }
        str.delete(str.length() - 2, str.length());
        str.append(']');

        return str.toString();
    }



    /**
     * This method compares the team with an object.
     * 
     * @param o The object to compare.
     * @return Return true if the team is equal to the object, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team oTeam = (Team) o;
        return oTeam.name.equals(this.name) && oTeam.teammates.equals(this.teammates);
    }



    /**
     * This method returns the team's hash code.
     * 
     * @return Return the team's hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.teammates);
    }

}