package com.au2b2t.anarchycore.listeners;

import com.au2b2t.anarchycore.AnarchyCore;
import com.au2b2t.anarchycore.commands.ToggleConnectionMsgsCmd;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.InetAddress;

public class ConnectionEvents implements Listener {

    AnarchyCore plugin;

    public ConnectionEvents(AnarchyCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        for(Player player : Bukkit.getOnlinePlayers()){
            ToggleConnectionMsgsCmd.toggled.putIfAbsent(player.getUniqueId().toString(), true);
            if(ToggleConnectionMsgsCmd.toggled.get(player.getUniqueId().toString())){
                try {
                    if(!p.hasPlayedBefore()){
                        String firstJoinMsg = plugin.getConfig().getString("connection-messages.first-join-message").replace("%player%", p.getName());
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', firstJoinMsg));
                    }
                    String joinMsg = plugin.getConfig().getString("connection-messages.join-message").replace("%player%", p.getName());
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
                String quitMsg = plugin.getConfig().getString("connection-messages.quit-message").replace("%player%", e.getPlayer().getName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', quitMsg));
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent ev) {
        InetAddress addr = ev.getRealAddress();
        if (!plugin.getProxyUtils().canConnect(addr)) {
            ev.setKickMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("only-proxy-join.kick-message")));
            ev.setResult(PlayerLoginEvent.Result.KICK_WHITELIST);
        }
    }
}
