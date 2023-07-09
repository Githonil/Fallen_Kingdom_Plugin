package com.githonil.fallenkingdom;

import com.githonil.fallenkingdom.teams.TeamInterface;
import com.githonil.fallenkingdom.claims.ClaimInterface;

import com.githonil.fallenkingdom.listeners.blocks.BlockBreakListener;
import com.githonil.fallenkingdom.listeners.blocks.BlockPlaceListener;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class is the main class of the plugin.
 */
public class FallenKingdom extends JavaPlugin {

    /**
     * This attribute represents a player with his team.
     */
    private HashMap<UUID, TeamInterface> teammatesMap;



    /**
     * This attribute represents all the claims.
     */
    private Set<ClaimInterface> claims;



    /**
     * This method is activated when the plugin is loaded.
     */
    @Override
    public void onLoad() {
        teammatesMap = new HashMap<>();
        claims = new Set<>();

        getServer().getPluginManager().registerEvents(new BlockBreakListener(claims), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(claims), this);
    }



    /**
     * This method is activated when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        System.out.println("The fallen kingdom is ready !");
    }

}