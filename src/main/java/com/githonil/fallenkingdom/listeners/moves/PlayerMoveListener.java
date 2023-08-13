package com.githonil.fallenkingdom.listeners.moves;

import com.githonil.fallenkingdom.claims.ClaimInterface;

import java.util.Map;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;

/**
 * This class handles when a player moves.
 */
public class PlayerMoveListener implements Listener {

    /**
     * This attribute represents all the claims.
     */
    private Map<Chunk, ClaimInterface> claims;



    /**
     * The listener's constructor.
     * 
     * @param claims All the claims.
     */
    public PlayerMoveListener(Map<Chunk, ClaimInterface> claims) {
        this.claims = claims;
    }



    /**
     * This method handles when a player moves.
     * 
     * @param event The event's register.
     */
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location locationFrom = event.getFrom();
        Location locationTo = event.getTo();

        prepareEvent(player, locationFrom, locationTo, ChatColor.GREEN + "You go in a claim");
        prepareEvent(player, locationTo, locationFrom, ChatColor.RED + "You leave a claim");
    }



    /**
     * This method prepares the event to in/out of a claim.
     * 
     * @param player The player.
     * @param locationFrom The departure outside of a claim.
     * @param locationTo The arrival inside of a claim.
     * @param msg The msg.
     */
    private void prepareEvent(Player player, Location locationFrom, Location locationTo, String msg) {
        if (claims.get(locationFrom.getChunk()) != null || claims.get(locationTo.getChunk()) == null) return;
        
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(msg));
    }

}