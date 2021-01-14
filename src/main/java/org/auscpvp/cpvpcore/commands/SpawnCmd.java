package org.auscpvp.cpvpcore.commands;

import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class SpawnCmd implements CommandExecutor {

    CpvpCore plugin;
    DecimalFormat format = new DecimalFormat("#.#");

    public SpawnCmd(CpvpCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length == 0){
            if(plugin.getConfig().getBoolean("spawn.enabled")){
                try {
                    World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.location.world"));
                    double x = plugin.getConfig().getDouble("spawn.location.x");
                    double y = plugin.getConfig().getDouble("spawn.location.y");
                    double z = plugin.getConfig().getDouble("spawn.location.z");
                    float yaw = plugin.getConfig().getInt("spawn.location.yaw");
                    Location loc = new Location(w, x, y, z, yaw, 0);
                    p.teleport(loc);
                } catch (Exception ignored) {}
            } else {
                String str = plugin.getConfig().getString("messages.command-disabled");
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
            }
        } else if(args[0].equalsIgnoreCase("set")){
            if (p.hasPermission("cpvpcore.setspawn")) {
                try {
                    plugin.getConfig().set("spawn.location.world", p.getLocation().getWorld().getName());
                    plugin.getConfig().set("spawn.location.x", Double.parseDouble(format.format(p.getLocation().getX())));
                    plugin.getConfig().set("spawn.location.y", Double.parseDouble(format.format(p.getLocation().getY())));
                    plugin.getConfig().set("spawn.location.z", Double.parseDouble(format.format(p.getLocation().getZ())));
                    plugin.getConfig().set("spawn.location.yaw", Double.parseDouble(format.format(p.getLocation().getYaw())));
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.GREEN + "World spawn set to " + spawnFormatted());
                } catch (Exception ex){
                    p.sendMessage(ChatColor.RED + "ERROR FAILED !");
                }
            } else {
                p.sendMessage(ChatColor.RED + "You do not have the permission.");
                return false;
            }
            return true;
        }
        return false;
    }

    public String spawnFormatted(){
        final String x = format.format(plugin.getConfig().getDouble("spawn.location.x"));
        final String y = format.format(plugin.getConfig().getDouble("spawn.location.y"));
        final String z = format.format(plugin.getConfig().getDouble("spawn.location.z"));
        return x + ", " + y + ", " + z;
    }
}
