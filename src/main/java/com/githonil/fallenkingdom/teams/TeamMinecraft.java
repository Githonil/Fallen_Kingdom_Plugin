package com.githonil.fallenkingdom.teams;

import java.util.UUID;
import java.io.Serial;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.OfflinePlayer;

/**
 * This class represent a team for the game Minecraft.
 */
public class TeamMinecraft extends Team {

    /**
     * The scoreboard for the teams.
     */
    public static Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();



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
     * This attribute represent the format color. Useful for the saveGame.
     */
    private String colorString;



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
        colorString = color.name();
        this.team.setColor(color);
        this.team.setPrefix(color + this.getName() + " | ");
        Player player = Bukkit.getOfflinePlayer(leader).getPlayer();
        player.setDisplayName(color + super.getName() + " | " + player.getName() + ChatColor.RESET);
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
            this.team = scoreboard.registerNewTeam(super.getName());

        Player player = Bukkit.getOfflinePlayer(teammate).getPlayer();
        player.setScoreboard(scoreboard);
        player.setDisplayName(color + super.getName() + " | " + player.getName() + ChatColor.RESET);

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
        player.setDisplayName(player.getName());

        team.removeEntry(player.getName());

        if (team.getSize() <= 0) {
            team.unregister();
            team = null;
        }
    }



    /**
     * This method return the color of the team.
     * 
     * @return Return the color of the team.
     */
    public String getColor() {
        return colorString;
    }



    /**
     * This method reload a teammate if attribut is transient.
     * 
     * @param teammate The teammate to reload.
     */
    public void reloaderTeammate(UUID teammate) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(teammate);
        
        if (player.isOnline())
            player.getPlayer().setDisplayName(color + super.getName() + " | " + player.getName() + ChatColor.RESET);
        
        team.addEntry(player.getName());
    }



    /**
     * This method reload the team if attribut is transient.
     */
    @Override
    public void reloaderTeam() {
        this.team = scoreboard.registerNewTeam(super.getName());
        color = ChatColor.valueOf(this.colorString);
        this.team.setColor(color);
        this.team.setPrefix(color + super.getName() + " | ");

        for (UUID playerUUID : this) {
            reloaderTeammate(playerUUID);
        }
    }

}