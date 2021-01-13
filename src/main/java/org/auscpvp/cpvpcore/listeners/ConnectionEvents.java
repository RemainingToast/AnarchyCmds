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
        if(plugin.getConfig().getBoolean("connectionmsgs.enabled")){
            String configMsg = plugin.getConfig().getString("connectionmsgs.joinmessage");
            String joinMsg = "";
            if (configMsg != null) {
                joinMsg = ChatColor.translateAlternateColorCodes('&', configMsg.replace("%player%", p.getName()));
            }
            if(plugin.getConfig().getBoolean("connectionmsgs.firstjoinalert")){
                String configMsg1 = plugin.getConfig().getString("connectionmsgs.firstjoinmessage");
                String firstJoinMsg = "";
                if (configMsg1 != null) {
                    firstJoinMsg = ChatColor.translateAlternateColorCodes('&', configMsg1.replace("%player%", p.getName()));
                }
                if(!p.hasPlayedBefore()){
                    for(Player online : Bukkit.getOnlinePlayers()){
                        online.sendMessage(firstJoinMsg);
                    }
                }
            }
            e.setJoinMessage(joinMsg);
        }
        if(plugin.getConfig().getBoolean("spawnteleport.onjoin")){
            String str = plugin.getConfig().getString("spawnteleport.world");
            if(str != null){
                World w = Bukkit.getServer().getWorld(str);
                if(w != null){
                    double x = plugin.getConfig().getDouble("spawnteleport.x");
                    double y = plugin.getConfig().getDouble("spawnteleport.y");
                    double z = plugin.getConfig().getDouble("spawnteleport.z");
                    float yaw = plugin.getConfig().getInt("spawnteleport.yaw");
                    p.teleport(new Location(w, x, y, z, yaw, 0));
                }
            }
        }
    }

}
