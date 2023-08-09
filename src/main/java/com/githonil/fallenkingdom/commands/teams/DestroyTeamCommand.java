package com.githonil.fallenkingdom.commands.teams;

import com.githonil.fallenkingdom.teams.TeamInterface;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
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
     * @return Return true if the command is correctly executed.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length == 0) return commandEveryone(player, args);
        else if (args.length == 1) return commandOperator(player, args);

        return false;
    }



    /**
     * This method handles the command "destroyteam" for everyone.
     * 
     * @param player The player who executed the command.
     * @param args The arguments.
     * @return Retur true if the command is correctly executed.
     */
    private boolean commandEveryone(Player player, String[] args) {
        UUID playerUUID = player.getUniqueId();
        TeamInterface team = teammatesMap.get(playerUUID);

        if (!(team != null && team.getLeader().equals(playerUUID))) {
            player.sendMessage(ChatColor.RED + "You are not a leader");
            return false;
        }

        deleteTeam(team);

        player.sendMessage(ChatColor.GREEN + "Your team is destroy");

        return true;
    }



    /**
     * This method handles the the command "destroyteam" for operator.
     * 
     * @param player The player who executed the command.
     * @param args The arguments.
     * @return Retur true if the command is correctly executed.
     */
    private boolean commandOperator(Player player, String[] args) {
        if (!player.hasPermission("fallenkingdom.operator")) {
            player.sendMessage(ChatColor.RED + "You don't have the permission");
            return false;
        };

        String name = args[0];
        for (TeamInterface team : teammatesMap.values()) {
            if (team.getName().equals(name)) {
                deleteTeam(team);
                player.sendMessage(ChatColor.GREEN + "The team is destroy");
                return true;
            }
        }

        player.sendMessage(ChatColor.RED + "The team doesn't exist");
        return false;
    }



    /**
     * This method delete a team.
     * 
     * @param team The team.
     */
    private void deleteTeam(TeamInterface team) {
        for (UUID teammate : team) {
            Player playerTmp = Bukkit.getOfflinePlayer(teammate).getPlayer();
            this.fireTeammate(team, playerTmp);
        }

        UUID leader = team.getLeader();
        Player playerLeader = Bukkit.getOfflinePlayer(leader).getPlayer();
        fireTeammate(team, playerLeader);
    }



    /**
     * This method fires a teammate in his team.
     * 
     * @param player The player.
     */
    private void fireTeammate(TeamInterface team, Player player) {
        UUID playerUUID = player.getUniqueId();
        team.removeTeammate(playerUUID);
        teammatesMap.remove(playerUUID);
    }

}