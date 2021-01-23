package com.au2b2t.anarchycore.listeners;

import com.au2b2t.anarchycore.AnarchyCore;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.List;
import java.util.Random;

public class ServerEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPing(ServerListPingEvent e){
        if(AnarchyCore.INSTANCE.getConfig().getBoolean("random-motd")){
            List<String> list = AnarchyCore.INSTANCE.getConfig().getStringList("messages.motd-messages");
            Random random = new Random();
            e.setMotd(ChatColor.translateAlternateColorCodes('&', list.get(random.nextInt(list.size()))));
        }
    }
}
