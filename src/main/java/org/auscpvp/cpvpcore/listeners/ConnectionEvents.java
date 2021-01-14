package org.auscpvp.cpvpcore.listeners;

import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class ConnectionEvents implements Listener , CommandExecutor {

    HashMap<String, Boolean> toggled = new HashMap<>();

    CpvpCore plugin;

    public ConnectionEvents(CpvpCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        for(Player player : Bukkit.getOnlinePlayers()){
            toggled.putIfAbsent(player.getUniqueId().toString(), true);
            if(toggled.get(player.getUniqueId().toString())){
                try {
                    if(!p.hasPlayedBefore()){
                        String firstJoinMsg = plugin.getConfig().getString("spawn.messages.first-join-message").replace("%player%", p.getName());
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', firstJoinMsg));
                    }
                    String joinMsg = plugin.getConfig().getString("spawn.messages.join-message").replace("%player%", p.getName());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', joinMsg));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        if(plugin.getConfig().getBoolean("spawn.teleport-onjoin")){
            try {
                World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.location.world"));
                double x = plugin.getConfig().getDouble("spawn.location.x");
                double y = plugin.getConfig().getDouble("spawn.location.y");
                double z = plugin.getConfig().getDouble("spawn.location.z");
                float yaw = plugin.getConfig().getInt("spawn.location.yaw");
                p.teleport(new Location(w, x, y, z, yaw, 0));
            } catch (Exception ex){

            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
        for (Player player : Bukkit.getOnlinePlayers()){
            if (toggled.get(player.getUniqueId().toString())){
                String quitMsg = plugin.getConfig().getString("spawn.messages.quit-message").replace("%player%", e.getPlayer().getName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', quitMsg));
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(plugin.getConfig().getBoolean("toggle-connection-msgs.enabled")){
            if(!(sender instanceof Player)){
                sender.sendMessage("The console can not toggle connection messages.");
                return false;
            } else {
                try {
                    Player p = (Player) sender;
                    String id = p.getUniqueId().toString();
                    if(toggled.get(id)){
                        String off = plugin.getConfig().getString("toggle-connection-msgs.off-msg");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', off));
                        toggled.replace(id, false);
                    } else {
                        String on = plugin.getConfig().getString("toggle-connection-msgs.on-msg");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', on));
                        toggled.replace(id, true);
                    }
                    return true;
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
        return false;
    }
}
