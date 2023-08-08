package com.githonil.fallenkingdom.commands;

import com.githonil.fallenkingdom.teams.TeamInterface;
import com.githonil.fallenkingdom.teams.TeamMinecraft;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

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
     * @return Return true if the command is correctly executed.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2 && !(sender instanceof Player)) return false;

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();
        String name = args[0];
        String colorName = args[1].toUpperCase();

        ChatColor color = this.getColor(colorName);
        if (!this.check(color, player)) return false;

        //String newPseudo = color + name + " | " + player.getName() + ChatColor.WHITE;
        //this.changeName(player, newPseudo);


        //Team team = new Team(name, colorName, playerUUID);
        TeamMinecraft team = new TeamMinecraft(name, playerUUID, color);
        teammatesMap.put(playerUUID, team);

        player.sendMessage(ChatColor.GREEN + "Your team is create");

        return true;
    }



    /**
     * This method checks the command.
     * 
     * @param color The color to check.
     * @param playerUUID The player to check.
     * @return Return false if a problem appears.
     */
    private boolean check(ChatColor color, Player player) {
        if (color == null) {
            player.sendMessage(ChatColor.RED + "This color doesn't exist");
            return false;
        }

        if (teammatesMap.get(player.getUniqueId()) != null) {
            player.sendMessage(ChatColor.RED + "You have already a team");
            return false;
        }

        return true;
    }



    /**
     * This method gives a color related to the name.
     * 
     * @param colorName The name of the color.
     * @return Return the color or null if no color related to the name.
     */
    private ChatColor getColor(String colorName) {
        try {
            return ChatColor.valueOf(colorName);
        }
        catch (Exception exception) {
            return null;
        }
    }

}