package org.auscpvp.cpvpcore.commands;

import net.md_5.bungee.api.ChatColor;
import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmcCmd implements CommandExecutor {

    CpvpCore plugin;

    public GmcCmd(CpvpCore plugin) {
       this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(p != null){
            if(plugin.getConfig().getBoolean("gamemode-aliases.enabled")){
                p.performCommand("gamemode creative");
            } else {
                String str = plugin.getConfig().getString("messages.command-disabled");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
            }
        }
        return false;
    }
}
