package com.github.anarchyplugins.anarchycore.commands;

import com.github.anarchyplugins.anarchycore.AnarchyCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCmd implements CommandExecutor {

    AnarchyCore plugin;

    public DiscordCmd(AnarchyCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(plugin.getConfig().getBoolean("discord-command")){
            String str = plugin.getConfig().getString("messages.discord-message");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
            return true;
        }
        return false;
    }
}
