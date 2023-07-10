package com.githonil.fallenkingdom.commands;

import com.githonil.fallenkingdom.teams.TeamInterface;
import com.githonil.fallenkingdom.teams.Team;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import org.bukkit.entity.Player;

/**
 * This class handles when the command "createTeam" is called.
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
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        String name = args[0];
        String color = args[1];
        UUID playerUUID = player.getUniqueId();

        Team team = new Team(name, color, playerUUID);
        teammatesMap.put(playerUUID, team);
        
        return true;
    }

}