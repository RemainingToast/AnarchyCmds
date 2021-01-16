package org.auscpvp.cpvpcore.commands;

import org.auscpvp.cpvpcore.CpvpCore;
import org.auscpvp.cpvpcore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCmd implements CommandExecutor {

    CpvpCore plugin;

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
                    return true;
                } catch (Exception ignored) {}
            } else {
                String str = plugin.getConfig().getString("messages.command-disabled");
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
            }
        }
        return false;
    }
}
