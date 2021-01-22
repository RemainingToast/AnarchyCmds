package com.au2b2t.anarchycore.commands;

import com.au2b2t.anarchycore.AnarchyCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class HelpCmd implements CommandExecutor {

    AnarchyCore plugin;

    public HelpCmd(AnarchyCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(plugin.getConfig().getBoolean("help.enabled")){
            List<String> lines = plugin.getConfig().getStringList("help.message");
            String str = String.join("\n", lines);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
        } else {
            String str = plugin.getConfig().getString("messages.command-disabled");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
        }
        return true;
    }
}
