package com.github.anarchyplugins.anarchycore.utils;

import com.github.anarchyplugins.anarchycore.Main;
import com.github.anarchyplugins.anarchycore.commands.ToggleConnectionMsgs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        for (Player vanished: Main.INSTANCE.gamemodelist.keySet()){
            p.hidePlayer(Main.INSTANCE, vanished);
        }
        if(Main.INSTANCE.getConfig().getBoolean("in-game-motd")){
            String motd = Main.INSTANCE.getConfig().getString("messages.in-game-motd");
            motd = motd.replaceAll("%player%", p.getName());
            Util.sendMessage(p, motd);
        }
        for(Player player : Bukkit.getOnlinePlayers()){
            ToggleConnectionMsgs.toggled.putIfAbsent(player.getUniqueId().toString(), true);
            if(ToggleConnectionMsgs.toggled.get(player.getUniqueId().toString())){
                try {
                    String joinMsg = Main.INSTANCE.getConfig().getString("messages.join-message").replace("%player%", p.getName());
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
            if (ToggleConnectionMsgs.toggled.get(player.getUniqueId().toString())){
                String quitMsg = Main.INSTANCE.getConfig().getString("messages.quit-message").replace("%player%", e.getPlayer().getName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', quitMsg));
            }
            if(Main.INSTANCE.isVanished(player)) Util.unvanishPlayer(player);
        }
    }
}
