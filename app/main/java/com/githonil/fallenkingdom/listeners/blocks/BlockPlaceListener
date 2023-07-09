package com.githonil.fallenkingdom.listeners.blocks;

import com.githonil.fallenkingdom.claims.ClaimInterface;

import java.util.Set;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Chunk;

/**
 * The class handles when a block is placed.
 */
public class BlockPlaceListener extends Listener {

    /**
     * This attribute represents all the claims.
     */
    private Set<ClaimInterface> claims;



    /**
     * The listener's constructor.
     * 
     * @param claims All the claims.
     */
    public BlockPlaceListener(Set<ClaimInterface> claims) {
        this.claims = claims;
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
                return true;
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

        if (claim == null || !claim.checkPlayer(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }

}