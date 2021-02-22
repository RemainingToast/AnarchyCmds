package com.github.anarchycmds.commands;

import com.github.anarchycmds.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kill implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(Main.INSTANCE.getConfig().getBoolean("kill-command")){
            if(p != null){
                p.setHealth(0);
                return true;
            }
        } else {
            String str = Main.INSTANCE.getConfig().getString("messages.command-disabled");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
        }
        return false;
    }
}
