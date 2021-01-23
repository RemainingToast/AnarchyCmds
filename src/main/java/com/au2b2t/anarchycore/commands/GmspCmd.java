package com.au2b2t.anarchycore.commands;

import com.au2b2t.anarchycore.AnarchyCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmspCmd implements CommandExecutor {

    AnarchyCore plugin;

    public GmspCmd(AnarchyCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(p != null){
            if(plugin.getConfig().getBoolean("gamemode-aliases-cmd")){
                p.performCommand("gamemode spectator");
            } else {
                String str = plugin.getConfig().getString("messages.command-disabled");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
            }
        }
        return false;
    }

}
