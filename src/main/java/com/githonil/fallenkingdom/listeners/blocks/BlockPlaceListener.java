package com.githonil.fallenkingdom.listeners.blocks;

import com.githonil.fallenkingdom.claims.ClaimInterface;

import java.util.Map;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Chunk;
import org.bukkit.Material;

/**
 * The class handles when a block is placed.
 */
public class BlockPlaceListener implements Listener {

    /**
     * This attribute represents all the claims.
     */
    private Map<Chunk, ClaimInterface> claims;



    /**
     * This attribute represents the plugin's configuration.
     */
    private FileConfiguration config;



    /**
     * The listener's constructor.
     * 
     * @param claims All the clagims.
     * @param config The config.
     */
    public BlockPlaceListener(Map<Chunk, ClaimInterface> claims, FileConfiguration config) {
        this.claims = claims;
        this.config = config;
    }



    /**
     * This method handles when a block is placed.
     * 
     * @param event The event's register.
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        Player player = event.getPlayer();
        ClaimInterface claim = claims.get(block.getChunk());

        boolean blocksListCanPlaceOutside = config.getStringList("blocksListCanPlaceOutside").contains(block.getType().toString());
        boolean blocksListCannotPlaceInside = config.getStringList("blocksListCannotPlaceInside").contains(block.getType().toString());

        if (block.getType().equals(Material.TNT) || blocksListCanPlaceOutside)
            return;

        if (claim == null || !claim.checkPlayer(player.getUniqueId()) || blocksListCannotPlaceInside) {
            event.setCancelled(true);
        }
    }

}