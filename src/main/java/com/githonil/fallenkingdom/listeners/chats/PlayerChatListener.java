package com.githonil.fallenkingdom.listeners.chats;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * This class handles when a player send a message in the chat.
 */
public class PlayerChatListener implements Listener {

    /**
     * This method handles when a player send a message in the chat.
     * 
     * @param event The event's register.
     */
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setFormat(event.getPlayer().getDisplayName() + " : " + event.getMessage());
    }

}