package com.githonil.fallenkingdom.teams;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.entity.Player;

/**
 * This class represent a team for the game Minecraft.
 */
public class TeamMinecraft extends Team {

    /**
     * This attribute represents the team in Minecraft.
     */
    private org.bukkit.scoreboard.Team team;



    /**
     * This attribute represents the team's color.
     */
    private ChatColor color;



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
        Player player = Bukkit.getOfflinePlayer(super.getLeader()).getPlayer();
        player.setDisplayName(color + super.getName() + " | " + player.getName() + ChatColor.RESET);
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
        player.setDisplayName(color + super.getName() + " | " + player.getName() + ChatColor.RESET);
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
        player.setDisplayName(player.getName());

        if (team.getSize() <= 0) {
            team.unregister();
            team = null;
        }
    }

}