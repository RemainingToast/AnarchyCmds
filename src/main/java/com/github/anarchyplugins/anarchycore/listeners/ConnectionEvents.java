package com.github.anarchyplugins.anarchycore.listeners;

import com.github.anarchyplugins.anarchycore.AnarchyCore;
import com.github.anarchyplugins.anarchycore.commands.ToggleConnectionMsgsCmd;
import com.github.anarchyplugins.anarchycore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionEvents implements Listener {

    AnarchyCore plugin;

    public ConnectionEvents(AnarchyCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        if(plugin.getConfig().getBoolean("in-game-motd")){
            String motd = plugin.getConfig().getString("messages.in-game-motd");
            motd = motd.replaceAll("%player%", p.getName());
            Util.sendMessage(p, motd);
        }
        for(Player player : Bukkit.getOnlinePlayers()){
            ToggleConnectionMsgsCmd.toggled.putIfAbsent(player.getUniqueId().toString(), true);
            if(ToggleConnectionMsgsCmd.toggled.get(player.getUniqueId().toString())){
                try {
                    String joinMsg = plugin.getConfig().getString("messages.join-message").replace("%player%", p.getName());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinMsg));
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
        for (Player player : Bukkit.getOnlinePlayers()){
            if (ToggleConnectionMsgsCmd.toggled.get(player.getUniqueId().toString())){
                String quitMsg = plugin.getConfig().getString("messages.quit-message").replace("%player%", e.getPlayer().getName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', quitMsg));
            }
        }
    }
}
