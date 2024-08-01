package com.githonil.fallenkingdom.listeners.connections;

import com.githonil.fallenkingdom.teams.TeamMinecraft;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.entity.Player;

/**
 * This class handles when a player joins the server.
 */
public class PlayerJoinListener implements Listener {

    /**
     * This class handles when a player joins the server.
     * 
     * @param event The event's register.
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setScoreboard(TeamMinecraft.scoreboard);
    }

}