package com.githonil.fallenkingdom.listeners.blocks;

import com.githonil.fallenkingdom.claims.ClaimInterface;

import java.util.HashSet;

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
    private HashSet<ClaimInterface> claims;



    /**
     * This attribute represents the plugin's configuration.
     */
    private FileConfiguration config;



    /**
     * The listener's constructor.
     * 
     * @param claims All the clagims.
     */
    public BlockPlaceListener(HashSet<ClaimInterface> claims, FileConfiguration config) {
        this.claims = claims;
        this.config = config;
    }



    /**
     * This method checks if a block is in the claims list.
     * 
     * @param block The block.
     * @return Return the claim where is the block if the block is in the claims list, else null.
     */
    private ClaimInterface checkBlock(Block block) {
        Chunk chunk = block.getChunk();
        Block blockBaseChunk = chunk.getBlock(0, 0, 0);
        for(ClaimInterface claim : claims) {
            int[] coordinate = claim.getCoordinate();
            if (coordinate[0] == blockBaseChunk.getX() && coordinate[1] == blockBaseChunk.getY() && coordinate[2] == blockBaseChunk.getZ())
                return claim;
        }
        return null;
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
        ClaimInterface claim = checkBlock(block);

        boolean blocksListCanPlaceOutside = config.getStringList("blocksListCanPlaceOutside").contains(block.getType().toString());
        boolean blocksListCannotPlaceInside = config.getStringList("blocksListCannotPlaceInside").contains(block.getType().toString());

        if (block.getType().equals(Material.TNT) || blocksListCanPlaceOutside)
            return;

        if (claim == null || !claim.checkPlayer(player.getUniqueId()) || blocksListCannotPlaceInside) {
            event.setCancelled(true);
        }
    }

}