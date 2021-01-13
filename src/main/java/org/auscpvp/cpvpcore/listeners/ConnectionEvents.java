package org.auscpvp.cpvpcore.listeners;

import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionEvents implements Listener {

    CpvpCore plugin;

    public ConnectionEvents(CpvpCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        boolean enabled = !plugin.getConfig().getString("messages.join-message").isEmpty();
        if(enabled){
            String str = plugin.getConfig().getString("messages.join-message");
            String joinMsg = ChatColor.translateAlternateColorCodes('&', str.replace("%player%", p.getName()));;
            boolean enabled1 = !plugin.getConfig().getString("messages.first-join-message").isEmpty();
            if(enabled1){
                String firstJoinMsg = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.first-join-message").replace("%player%", p.getName()));
                if(!p.hasPlayedBefore()){
                    for(Player online : Bukkit.getOnlinePlayers()){
                        online.sendMessage(firstJoinMsg);
                    }
                }
            }
            e.setJoinMessage(joinMsg);
        }
        if(plugin.getConfig().getBoolean("commands.spawn.teleport-onjoin")){
            try {
                World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("commands.spawn.location.world"));
                double x = plugin.getConfig().getDouble("commands.spawn.location.x");
                double y = plugin.getConfig().getDouble("commands.spawn.location.y");
                double z = plugin.getConfig().getDouble("commands.spawn.location.z");
                float yaw = plugin.getConfig().getInt("commands.spawn.location.yaw");
                p.teleport(new Location(w, x, y, z, yaw, 0));
            } catch (Exception ex){

            }
        }
    }

}
