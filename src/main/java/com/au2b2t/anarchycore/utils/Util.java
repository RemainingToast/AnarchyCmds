package com.au2b2t.anarchycore.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {

    private static String prefix = "";

    public static String getPrefix() {
        return prefix;
    }

    public static double getTps(){
        return Math.round(Bukkit.getServer().getTPS()[0]);
    }

    public static void setPrefix(String prefix) {
        Util.prefix = prefix;
    }

    public static void sendMessage(Player player, String string) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
    }

    public static void sendMessage(CommandSender sender, String string) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
    }

    public static void sendMessagePrefix(Player player, String string) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + string));
    }

    public static void sendMessagePrefix(CommandSender sender, String string) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + string));
    }

}
