package com.github.anarchyplugins.anarchycore.commands;

import com.github.anarchyplugins.anarchycore.Main;
import com.github.anarchyplugins.anarchycore.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Main.INSTANCE.isVanished(player)) {
                if (player.hasPermission("anarchycore.vanish")) {
                    Util.unvanishPlayer(player);

                    player.sendMessage("You are now unvanished!");
                } else {
                    sender.sendMessage("You have no permission to do that!");
                }
            } else {
                if (player.hasPermission("anarchycore.vanish")) {
                    Util.vanishPlayer(player);

                    player.sendMessage("You are now vanished!");
                } else {
                    sender.sendMessage("You have no permission to do that!");
                }
            }
        } else {
            sender.sendMessage("You need to be a player to do that!");
        }

        return false;
    }
}
