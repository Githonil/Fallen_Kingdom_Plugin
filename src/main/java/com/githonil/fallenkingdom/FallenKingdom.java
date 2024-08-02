package com.githonil.fallenkingdom;

import com.githonil.fallenkingdom.teams.TeamInterface;
import com.githonil.fallenkingdom.claims.ClaimInterface;

import com.githonil.fallenkingdom.listeners.blocks.BlockBreakListener;
import com.githonil.fallenkingdom.listeners.blocks.BlockPlaceListener;
import com.githonil.fallenkingdom.listeners.moves.PlayerMoveListener;
import com.githonil.fallenkingdom.listeners.connections.PlayerJoinListener;
import com.githonil.fallenkingdom.listeners.chats.PlayerChatListener;

import com.githonil.fallenkingdom.commands.teams.CreateTeamCommand;
import com.githonil.fallenkingdom.commands.teams.DestroyTeamCommand;
import com.githonil.fallenkingdom.commands.claims.ClaimCommand;
import com.githonil.fallenkingdom.commands.claims.UnclaimCommand;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Chunk;

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
    private HashMap<Chunk, ClaimInterface> claims;



    /**
     * This is the save class.
     */
    private SaveGame saveGame;



    /**
     * The path of teams save.
     */
    private final String pathTeams = "./plugins/Fallen_Kingdom/teams.save";



    /**
     * The path of claims save.
     */
    private final String pathClaims = "./plugins/Fallen_Kingdom/claims.save";



    /**
     * This method is activated when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        saveGame = new SaveGame();
        teammatesMap = (HashMap<UUID, TeamInterface>) saveGame.loadTeam(pathTeams);
        claims = (HashMap<Chunk, ClaimInterface>) saveGame.loadClaims(pathClaims);

        System.out.println(teammatesMap);

        this.saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(claims), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(claims, this.getConfig()), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(claims), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(teammatesMap), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);

        this.getCommand("createteam").setExecutor(new CreateTeamCommand(teammatesMap));
        this.getCommand("destroyteam").setExecutor(new DestroyTeamCommand(teammatesMap, claims));
        this.getCommand("claim").setExecutor(new ClaimCommand(claims, teammatesMap));
        this.getCommand("unclaim").setExecutor(new UnclaimCommand(claims, teammatesMap));
    }



    /**
     * This method is activated when the plugin is disabled.
     */
    @Override
    public void onDisable() {
        saveGame.save(teammatesMap, claims, pathTeams, pathClaims);
    }

}