package com.au2b2t.anarchycore.listeners;

import com.au2b2t.anarchycore.AnarchyCore;
import com.au2b2t.anarchycore.utils.PlayerDataUtilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvents implements Listener {

    AnarchyCore plugin;

    public ChatEvents(AnarchyCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        e.getRecipients().removeIf(r -> PlayerDataUtilities.loadPlayerData(r).hasIgnored(p.getUniqueId()));
    }

}
