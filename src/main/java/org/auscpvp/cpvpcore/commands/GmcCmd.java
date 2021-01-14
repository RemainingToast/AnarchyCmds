package org.auscpvp.cpvpcore.commands;

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
        if(plugin.getConfig().getBoolean("gamemode-aliases.enabled")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                p.performCommand("gamemode creative");
            }
        }
        return false;
    }
}
