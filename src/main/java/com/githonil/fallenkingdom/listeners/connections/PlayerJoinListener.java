package com.githonil.fallenkingdom.listeners.connections;

import com.githonil.fallenkingdom.teams.TeamInterface;

import java.util.Map;
import java.util.UUID;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.entity.Player;

/**
 * This class handles when a player joins the server.
 */
public class PlayerJoinListener implements Listener {

    /**
     * This attribute represents all players with their team.
     */
    private Map<UUID, TeamInterface> teammatesMap;



    /**
     * The listener's constructor.
     * 
     * @param teammatesMap All players with their team.
     */
    public PlayerJoinListener(Map<UUID, TeamInterface> teammatesMap) {
        this.teammatesMap = teammatesMap;
    }


    /**
     * This method handles when a player joins the server.
     * 
     * @param event The event's register.
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        teammatesMap.get(playerUUID).reloaderTeammate(playerUUID);
    }

}