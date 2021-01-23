package com.au2b2t.anarchycore.commands;

import com.au2b2t.anarchycore.AnarchyCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ToggleConnectionMsgsCmd implements CommandExecutor {

    public static HashMap<String, Boolean> toggled = new HashMap<>();

    AnarchyCore plugin;

    public ToggleConnectionMsgsCmd(AnarchyCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(plugin.getConfig().getBoolean("toggle-connection-msgs-cmd")){
            if(!(sender instanceof Player)){
                sender.sendMessage("The console can not toggle connection messages.");
                return false;
            } else {
                try {
                    Player p = (Player) sender;
                    String id = p.getUniqueId().toString();
                    if(toggled.get(id)){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.connection-cmd-off")));
                        toggled.replace(id, false);
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.connection-cmd-on")));
                        toggled.replace(id, true);
                    }
                    return true;
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        return false;
    }
}
