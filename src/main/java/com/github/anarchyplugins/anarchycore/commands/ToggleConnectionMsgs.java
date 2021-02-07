package com.github.anarchyplugins.anarchycore.commands;

import com.github.anarchyplugins.anarchycore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ToggleConnectionMsgs implements CommandExecutor {

    public static HashMap<String, Boolean> toggled = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(Main.INSTANCE.getConfig().getBoolean("toggle-connection-msgs-cmd")){
            if(!(sender instanceof Player)){
                sender.sendMessage("The console can not toggle connection messages.");
                return false;
            } else {
                try {
                    Player p = (Player) sender;
                    String id = p.getUniqueId().toString();
                    if(toggled.get(id)){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.INSTANCE.getConfig().getString("messages.connection-cmd-off")));
                        toggled.replace(id, false);
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.INSTANCE.getConfig().getString("messages.connection-cmd-on")));
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
