package com.githonil.fallenkingdom.commands;

import com.githonil.fallenkingdom.claims.ClaimInterface;
import com.githonil.fallenkingdom.claims.Claim;

import com.githonil.fallenkingdom.teams.TeamInterface;

import java.util.UUID;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import org.bukkit.entity.Player;
import org.bukkit.Chunk;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;

/**
 * This class handles when the command "claim" is called.
 */
public class ClaimCommand implements CommandExecutor {

    /**
     * This attribute represents all the claims.
     */
    private HashSet<ClaimInterface> claims;



    /**
     * This attribute represents all players with their team.
     */
    private HashMap<UUID, TeamInterface> teammatesMap;



    /**
     * The command's constructor.
     * 
     * @param claims All the claims.
     * @param teammatesMap All the players with their team.
     */
    public ClaimCommand(HashSet<ClaimInterface> claims, HashMap<UUID, TeamInterface> teammatesMap) {
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
        TeamInterface team = this.checkPlayer(player);

        if (team == null) {
            player.sendMessage(ChatColor.RED + "You need to be a leader for this command");
            return false;
        }

        Chunk chunk = player.getLocation().getChunk();
        boolean success = this.claimChunk(chunk, team);

        this.displayPlayer(success, player);

        return true;
    }



    /**
     * This method check if the player can use the command.
     * 
     * @param player The player.
     * @return Return the player's team.
     */
    private TeamInterface checkPlayer(Player player) {
        UUID playerUUID = player.getUniqueId();
        TeamInterface team = teammatesMap.get(playerUUID);
        if (team == null) return null;

        if (team.getLeader().equals(playerUUID)) return team;

        return null;
    }



    /**
     * This method claims and reference the chunk.
     * 
     * @param player The chunk.
     * @param team The team who claim the chunk.
     * @return Return true if the chunk is claim, else false.
     */
    private boolean claimChunk(Chunk chunk, TeamInterface team) {
        Block block = chunk.getBlock(0, 0, 0);

        Claim claim = new Claim(block.getX(), block.getY(), block.getZ(), team);
        return claims.add(claim);
    }



    /**
     * This method display to the player if the chunk is claim.
     * 
     * @param success The result if the chunck is claim or not.
     * @param player The player.
     */
    private void displayPlayer(boolean success, Player player) {
        if (success) {
            player.sendMessage(ChatColor.GREEN + "The chunk is claim");
            return;
        }

        player.sendMessage(ChatColor.RED + "The chunk is already claim");
    }

}