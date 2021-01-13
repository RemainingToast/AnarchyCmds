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
            if(plugin.getConfig().getBoolean("commands.spawn.player-command")){
                try {
                    World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("commands.spawn.location.world"));
                    double x = plugin.getConfig().getDouble("commands.spawn.location.x");
                    double y = plugin.getConfig().getDouble("commands.spawn.location.y");
                    double z = plugin.getConfig().getDouble("commands.spawn.location.z");
                    float yaw = plugin.getConfig().getInt("commands.spawn.location.yaw");
                    p.teleport(new Location(w, x, y, z, yaw, 0));
                } catch (Exception ignored) {}
            }
        } else if(args[0].equalsIgnoreCase("set")){
            if (p.hasPermission("cpvpcore.setspawn")) {
                try {
                    plugin.getConfig().set("commands.spawn.location.world", p.getLocation().getWorld().getName());
                    plugin.getConfig().set("commands.spawn.location.x", p.getLocation().getX());
                    plugin.getConfig().set("commands.spawn.location.y", p.getLocation().getY());
                    plugin.getConfig().set("commands.spawn.location.z", p.getLocation().getZ());
                    plugin.getConfig().set("commands.spawn.location.yaw", p.getLocation().getYaw());
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
        final DecimalFormat format = new DecimalFormat("#.#");
        final String x = format.format(plugin.getConfig().getDouble("commands.spawn.location.x"));
        final String y = format.format(plugin.getConfig().getDouble("commands.spawn.location.y"));
        final String z = format.format(plugin.getConfig().getDouble("commands.spawn.location.z"));
        return x + ", " + y + ", " + z;
    }
}
