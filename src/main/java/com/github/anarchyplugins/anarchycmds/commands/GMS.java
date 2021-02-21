package com.github.anarchyplugins.anarchycmds.commands;

import com.github.anarchyplugins.anarchycmds.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMS implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(p != null){
            if(Main.INSTANCE.getConfig().getBoolean("gamemode-aliases-cmd")){
                p.performCommand("gamemode survival");
            } else {
                String str = Main.INSTANCE.getConfig().getString("messages.command-disabled");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
            }
        }
        return false;
    }

}
