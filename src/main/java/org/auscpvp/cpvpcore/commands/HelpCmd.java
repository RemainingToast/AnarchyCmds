package org.auscpvp.cpvpcore.commands;

import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class HelpCmd implements CommandExecutor {

    CpvpCore plugin;

    public HelpCmd(CpvpCore plugin){
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
