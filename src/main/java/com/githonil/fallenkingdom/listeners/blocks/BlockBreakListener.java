package com.githonil.fallenkingdom.listeners.blocks;

import com.githonil.fallenkingdom.claims.ClaimInterface;

import java.util.Map;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Chunk;

/**
 * The class handles when a block is breaking.
 */
public class BlockBreakListener implements Listener {

    /**
     * This attribute represents all the claims.
     */
    private Map<Chunk, ClaimInterface> claims;



    /**
     * The listener's constructor.
     * 
     * @param claims All the claims.
     */
    public BlockBreakListener(Map<Chunk, ClaimInterface> claims) {
        this.claims = claims;
    }



    /**
     * This method handles when a block is breaking.
     * 
     * @param event The event's register.
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        ClaimInterface claim = claims.get(block.getChunk());

        if (claim != null && !claim.checkPlayer(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }

}