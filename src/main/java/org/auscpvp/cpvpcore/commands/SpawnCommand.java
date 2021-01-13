package org.auscpvp.cpvpcore.commands;

import net.md_5.bungee.api.ChatColor;
import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class SpawnCommand implements CommandExecutor {

    CpvpCore plugin;

    public SpawnCommand(CpvpCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length == 0){
            if(plugin.getConfig().getBoolean("spawnteleport.command-enabled")){
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
        } else if(args[1].equalsIgnoreCase("set")){
            if (p.hasPermission("joinleave.setspawn")) {
                plugin.getConfig().set("spawn.world", p.getLocation().getWorld().getName());
                plugin.getConfig().set("spawn.x", p.getLocation().getX());
                plugin.getConfig().set("spawn.y", p.getLocation().getY());
                plugin.getConfig().set("spawn.z", p.getLocation().getZ());
                plugin.getConfig().set("spawn.yaw", p.getLocation().getYaw());
                plugin.saveConfig();
                p.sendMessage(ChatColor.GREEN + "World spawn set to " + spawnFormatted());
            } else {
                p.sendMessage(ChatColor.RED + "You do not have the permission.");
                return false;
            }
            return true;
        }
        return false;
    }

    public String spawnFormatted(){
        final DecimalFormat format = new DecimalFormat("#.#");
        final String x = format.format(plugin.getConfig().getDouble("spawn.x"));
        final String y = format.format(plugin.getConfig().getDouble("spawn.y"));
        final String z = format.format(plugin.getConfig().getDouble("spawn.z"));
        return x + ", " + y + ", " + z;
    }
}
