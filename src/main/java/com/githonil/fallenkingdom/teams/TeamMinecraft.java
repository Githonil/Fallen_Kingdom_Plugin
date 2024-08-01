package com.githonil.fallenkingdom.teams;

import java.util.UUID;
import java.io.Serial;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.entity.Player;

/**
 * This class represent a team for the game Minecraft.
 */
public class TeamMinecraft extends Team {

    /**
     * This attribute represents the ID for the serializable.
     */
    @Serial
    private static final long serialVersionUID = 77105110101L;



    /**
     * This attribute represents the team in Minecraft.
     */
    private transient org.bukkit.scoreboard.Team team;



    /**
     * This attribute represents the team's color.
     */
    private transient ChatColor color;



    /**
     * The team's constructor.
     * 
     * 
     * @param name The team's name.
     * @param leader The leader's UUID from the team.
     * @param color The team's color.
     */
    public TeamMinecraft(String name, UUID leader, ChatColor color) {
        super(name, leader);
        this.team.setColor(color);
        this.team.setPrefix(color + this.getName() + " | ");
        this.color = color;
    }



    /**
     * This method adds a teammate to the team.
     * 
     * @param teammate The teammate's UUID of the team.
     */
    @Override
    public void addTeammate(UUID teammate) {
        super.addTeammate(teammate);

        if (team == null)
            this.team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(super.getName());

        Player player = Bukkit.getOfflinePlayer(teammate).getPlayer();

        team.addEntry(player.getName());
    }



    /**
     * This method removes a teammate to the team.
     * 
     * @param teammate The teammate's UUID of the team.
     */
    @Override
    public void removeTeammate(UUID teammate) {
        if (team == null) return;

        super.removeTeammate(teammate);

        Player player = Bukkit.getOfflinePlayer(teammate).getPlayer();

        team.removeEntry(player.getName());

        if (team.getSize() <= 0) {
            team.unregister();
            team = null;
        }
    }

}