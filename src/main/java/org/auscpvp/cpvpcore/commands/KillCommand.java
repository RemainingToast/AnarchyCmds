package org.auscpvp.cpvpcore.commands;

import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand implements CommandExecutor {

    CpvpCore plugin;
    public KillCommand(CpvpCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(plugin.getConfig().getBoolean("commands.kill.enabled")){
            if(p != null){
                p.setHealth(-0.1);
                return true;
            }
        }
        return false;
    }
}
