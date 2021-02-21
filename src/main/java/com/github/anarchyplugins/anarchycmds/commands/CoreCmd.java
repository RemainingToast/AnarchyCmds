package com.github.anarchyplugins.anarchycmds.commands;

import com.github.anarchyplugins.anarchycmds.Main;
import com.github.anarchyplugins.anarchycmds.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Arrays;
import java.util.List;

public class CoreCmd implements TabExecutor {

    Main plugin = Main.INSTANCE;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("anarchycommands.core")){
            if(args.length > 0){
                switch (args[0].toLowerCase()){
                    case "reload": {
                        plugin.saveDefaultConfig();
                        plugin.reloadConfig();
                        Util.sendMessagePrefix(sender, "&aReloaded configuration file successfully.");
                        return true;
                    }
                    case "version": {
                        Util.sendMessagePrefix(sender, "&6Running version: &a" + plugin.getDescription().getVersion());
                        return true;
                    }
                    case "help": {
                        Util.sendMessagePrefix(sender, "&8---&r " + Util.getPrefix() + "&6Help &r&8---");
                        Util.sendMessagePrefix(sender, "&6 /ac reload &8-&r&3 Reloads the config");
                        Util.sendMessagePrefix(sender, "&6 /ac version &8-&r&3 Shows the version of the plugin");
                        Util.sendMessagePrefix(sender, "&6 /ac help &8-&r&3 Shows help for the plugin");
                        return true;
                    }
                }
            } else {
                Util.sendMessage(sender, Util.getPrefix() + "&6Running version: &a" + plugin.getDescription().getVersion());
            }
        } else {
            Util.sendMessage(sender, Util.getPrefix() + "&6Running version: &a" + plugin.getDescription().getVersion());
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            return Arrays.asList("reload", "version", "help");
        }
        return null;
    }

}
