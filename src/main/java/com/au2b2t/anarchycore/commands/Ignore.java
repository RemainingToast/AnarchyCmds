package com.au2b2t.anarchycore.commands;

import com.au2b2t.anarchycore.AnarchyCore;
import com.au2b2t.anarchycore.utils.PlayerDataUtilities;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/***
 * @author willemml
 */
public class Ignore implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            Player player = ((Player) sender).getPlayer();
            if (player == null) return false;
            UUID uuidToIgnore = AnarchyCore.INSTANCE.getServer().getPlayerUniqueId(args[0]);
            if (uuidToIgnore == null) {
                sender.sendMessage(ChatColor.RED + "No player with name " + args[0] + ".");
            } else {
                boolean ignored = PlayerDataUtilities.loadPlayerData(player).toggleIgnore(uuidToIgnore).save().hasIgnored(uuidToIgnore);
                if (ignored) {
                    sender.sendMessage(ChatColor.GREEN + "You are now ignoring " + args[0] + ".");
                    return true;
                }
                sender.sendMessage(ChatColor.GREEN + "You no longer ignoring " + args[0] + ".");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You must specify who you want to ignore.");
        }
        return true;
    }
}