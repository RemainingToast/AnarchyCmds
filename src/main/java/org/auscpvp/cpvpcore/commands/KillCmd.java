package org.auscpvp.cpvpcore.commands;

import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCmd implements CommandExecutor {

    CpvpCore plugin;
    public KillCmd(CpvpCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(plugin.getConfig().getBoolean("kill.enabled")){
            if(p != null){
                p.setHealth(0);
                return true;
            }
        } else {
            String str = plugin.getConfig().getString("messages.command-disabled");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
        }
        return false;
    }
}
