package com.githonil.fallenkingdom.commands.claims;

import com.githonil.fallenkingdom.claims.ClaimInterface;
import com.githonil.fallenkingdom.teams.TeamInterface;

import java.util.UUID;
import java.util.Map;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import org.bukkit.entity.Player;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.ChatColor;

/**
 * This class handles when the command "unclaim" is called.
 */
public class UnclaimCommand implements CommandExecutor {

    /**
     * This attribute represents all the claims.
     */
    private Map<Chunk, ClaimInterface> claims;



    /**
     * This attribute represents all players with their team.
     */
    private Map<UUID, TeamInterface> teammatesMap;



    /**
     * The command's constructor.
     * 
     * @param claims All the claims.
     * @param teammatesMap All the players with their team.
     */
    public UnclaimCommand(Map<Chunk, ClaimInterface> claims, Map<UUID, TeamInterface> teammatesMap) {
        this.claims = claims;
        this.teammatesMap = teammatesMap;
    }



    /**
     * The method handles when the command "claim" is called.
     * 
     * @param sender The sender.
     * @param command The command.
     * @param label The command's name.
     * @param args The arguments.
     * @return Return true if the command is correctly executed.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        ClaimInterface claim = this.checkPlayer(player);

        if (claim == null) {
            return false;
        }

        claims.remove(player.getLocation().getChunk());

        player.sendMessage(ChatColor.GREEN + "The chunk is unclaim");

        return true;
    }



    /**
     * This method checks if the player can unclaim the chunk.
     * 
     * @param player The player.
     * @return Return the claim if the player can unclaim, else null.
     */
    private ClaimInterface checkPlayer(Player player) {
        if (!this.isALeader(player)) return null;

        Chunk chunk = player.getLocation().getChunk();
        ClaimInterface claim = claims.get(chunk);
        
        if (!this.isCanBeUnclaim(claim, player)) return null;

        return claim;
    }



    /**
     * This method checks if the player is a leader.
     * 
     * @param player The player.
     * @return Return true if the player is a leader, else false.
     */
    private boolean isALeader(Player player) {
        UUID playerUUID = player.getUniqueId();
        TeamInterface team = teammatesMap.get(playerUUID);

        if (team == null || !team.getLeader().equals(playerUUID)) {
            player.sendMessage(ChatColor.RED + "You need to be a leader for this command");
            return false;
        }

        return true;
    }



    /**
     * This method chucks if the claim can be unclaim.
     * 
     * @param claim The claim.
     * @param player The player who executed the command.
     * @return return true if the claim can be unclaim, else false.
     */
    private boolean isCanBeUnclaim(ClaimInterface claim, Player player) {
        UUID playerUUID = player.getUniqueId();

        if (claim == null) {
            player.sendMessage(ChatColor.RED + "The chunk is not claim");
            return false;
        }

        if (!claim.checkPlayer(playerUUID)) {
            player.sendMessage(ChatColor.RED + "Is not your chunk");
            return false;
        }

        return true;
    }

}