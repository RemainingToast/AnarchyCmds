package org.auscpvp.cpvpcore.commands;

import org.auscpvp.cpvpcore.CpvpCore;
import org.auscpvp.cpvpcore.utils.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class CoreCmd implements TabExecutor {

    CpvpCore plugin;

    public CoreCmd(CpvpCore plugin){
        this.plugin = plugin;
    }

    DecimalFormat format = new DecimalFormat("#.#");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("cpvpcore.maincommand")){
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
                        Util.sendMessagePrefix(sender, "&6 /cpvpcore reload &8-&r&3 Reloads the config");
                        Util.sendMessagePrefix(sender, "&6 /cpvpcore version &8-&r&3 Shows the version of the plugin");
                        Util.sendMessagePrefix(sender, "&6 /cpvpcore help &8-&r&3 Shows help for the plugin");
                        Util.sendMessagePrefix(sender, "&6 /cpvpcore setspawn &8-&r&3 Set server spawn location");
                        return true;
                    }
                    case "setspawn": {
                        Player p = (Player) sender;
                        if (sender.hasPermission("cpvpcore.setspawn")) {
                            try {
                                plugin.getConfig().set("spawn.location.world", p.getLocation().getWorld().getName());
                                plugin.getConfig().set("spawn.location.x", Double.parseDouble(format.format(p.getLocation().getX())));
                                plugin.getConfig().set("spawn.location.y", Double.parseDouble(format.format(p.getLocation().getY())));
                                plugin.getConfig().set("spawn.location.z", Double.parseDouble(format.format(p.getLocation().getZ())));
                                plugin.getConfig().set("spawn.location.yaw", Double.parseDouble(format.format(p.getLocation().getYaw())));
                                plugin.saveConfig();
                                Util.sendMessagePrefix(p, ChatColor.GREEN + "World spawn set to " + spawnFormatted());
                            } catch (Exception ex){
                                Util.sendMessagePrefix(p, ChatColor.RED + "ERROR FAILED !");
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "You do not have the permission.");
                            return false;
                        }
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
            return Arrays.asList("reload", "version", "help", "setspawn");
        }
        return null;
    }

    public String spawnFormatted(){
        final String x = format.format(plugin.getConfig().getDouble("spawn.location.x"));
        final String y = format.format(plugin.getConfig().getDouble("spawn.location.y"));
        final String z = format.format(plugin.getConfig().getDouble("spawn.location.z"));
        return x + ", " + y + ", " + z;
    }
}
