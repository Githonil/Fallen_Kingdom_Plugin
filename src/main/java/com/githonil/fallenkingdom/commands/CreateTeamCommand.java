package com.githonil.fallenkingdom.commands;

import com.githonil.fallenkingdom.teams.TeamInterface;
import com.githonil.fallenkingdom.teams.Team;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

/**
 * This class handles when the command "createteam" is called.
 */
public class CreateTeamCommand implements CommandExecutor {

    /**
     * This attribute represents all players with their team.
     */
    private HashMap<UUID, TeamInterface> teammatesMap;



    /**
     * The command's constructor.
     * 
     * @param teammatesMap All players with their team.
     */
    public CreateTeamCommand(HashMap<UUID, TeamInterface> teammatesMap) {
        this.teammatesMap = teammatesMap;
    }



    /**
     * This method handles when the command "createTeam" is called.
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

        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Error for the command.");
            return false;
        }

        String name = args[0];
        String colorString = args[1].toUpperCase();
        ChatColor color;
        try {
            color = ChatColor.valueOf(colorString);
        }
        catch (Exception exception) {
            player.sendMessage(ChatColor.RED + "Error for the color.");
            return false;
        }
        UUID playerUUID = player.getUniqueId();

        Team team = new Team(name, colorString, playerUUID);
        teammatesMap.put(playerUUID, team);

        player.setDisplayName(color + name + " | " + player.getName() + ChatColor.WHITE);
        player.setPlayerListName(color + name + " | " + player.getName() + ChatColor.WHITE);

        player.sendMessage(ChatColor.GREEN + "Your team is create.");
        
        return true;
    }

}