package com.githonil.fallenkingdom;

import com.githonil.fallenkingdom.teams.TeamInterface;
import com.githonil.fallenkingdom.claims.ClaimInterface;

import com.githonil.fallenkingdom.listeners.blocks.BlockBreakListener;
import com.githonil.fallenkingdom.listeners.blocks.BlockPlaceListener;

import com.githonil.fallenkingdom.commands.teams.CreateTeamCommand;
import com.githonil.fallenkingdom.commands.teams.DestroyTeamCommand;
import com.githonil.fallenkingdom.commands.claims.ClaimCommand;
import com.githonil.fallenkingdom.commands.claims.UnclaimCommand;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class is the main class of the plugin.
 */
public class FallenKingdom extends JavaPlugin {

    /**
     * This attribute represents all players with their team.
     */
    private HashMap<UUID, TeamInterface> teammatesMap;



    /**
     * This attribute represents all the claims.
     */
    private HashSet<ClaimInterface> claims;



    /**
     * This method is activated when the plugin is loaded.
     */
    @Override
    public void onLoad() {
        teammatesMap = new HashMap<>();
        claims = new HashSet<>();
    }



    /**
     * This method is activated when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(claims), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(claims, this.getConfig()), this);

        this.getCommand("createteam").setExecutor(new CreateTeamCommand(teammatesMap));
        this.getCommand("destroyteam").setExecutor(new DestroyTeamCommand(teammatesMap));
        this.getCommand("claim").setExecutor(new ClaimCommand(claims, teammatesMap));
        this.getCommand("unclaim").setExecutor(new UnclaimCommand(claims, teammatesMap));
    }

}