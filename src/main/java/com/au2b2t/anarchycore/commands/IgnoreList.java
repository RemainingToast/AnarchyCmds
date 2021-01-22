package com.au2b2t.anarchycore.commands;

import com.au2b2t.anarchycore.AnarchyCore;
import com.au2b2t.anarchycore.utils.PlayerData;
import com.au2b2t.anarchycore.utils.PlayerDataUtilities;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class IgnoreList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player player = ((Player) sender).getPlayer();
            if (player == null) return false;
            PlayerData data = PlayerDataUtilities.loadExistingPlayerData(player.getUniqueId());
            player.sendMessage(ChatColor.RED.toString() + "Ignored Players:");
            int i = 0;
            for(UUID id : data.getIgnored()){
                String playerName = AnarchyCore.INSTANCE.getServer().getPlayer(id).getDisplayName();
                player.sendMessage(ChatColor.RED.toString() + ChatColor.ITALIC + playerName);
                i++;
            }
            player.sendMessage(ChatColor.RED.toString() + i + " players ignored.");
            return true;
        } catch (Exception ignored){
            return false;
        }
    }
}
