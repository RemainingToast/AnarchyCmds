package com.github.anarchycmds.commands;

import com.github.anarchycmds.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Help implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(Main.INSTANCE.getConfig().getBoolean("help-command")){
            List<String> lines = Main.INSTANCE.getConfig().getStringList("messages.help-message");
            String str = String.join("\n", lines);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
        } else {
            String str = Main.INSTANCE.getConfig().getString("messages.command-disabled");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
        }
        return true;
    }
}
