package com.githonil.fallenkingdom.commands;

import com.githonil.fallenkingdom.teams.TeamInterface;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

/**
 * This class handles when the command "detroyteam" is called.
 */
public class DestroyTeamCommand implements CommandExecutor {

    /**
     * This attribute represents all players with their team.
     */
    private HashMap<UUID, TeamInterface> teammatesMap;



    /**
     * The command's constructor.
     * 
     * @param teammatesMap All players with their team.
     */
    public DestroyTeamCommand(HashMap<UUID, TeamInterface> teammatesMap) {
        this.teammatesMap = teammatesMap;
    }



    /**
     * This method handles when the command "detroyteam" is called.
     * 
     * @param sender The sender.
     * @param command The command.
     * @param label The command's name.
     * @param args The arguments.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();
        TeamInterface team = teammatesMap.get(playerUUID);

        if (!team.getLeader().equals(playerUUID)) {
            player.sendMessage(ChatColor.RED + "You are not a leader");
            return false;
        }

        for (UUID teammate : team) {
            team.removeTeammate(teammate);
            teammatesMap.remove(teammate);
        }

        teammatesMap.remove(playerUUID);

        player.setDisplayName(player.getName());
        player.setPlayerListName(player.getName());

        player.sendMessage(ChatColor.GREEN + "Your team is destroy");

        return true;
    }

}