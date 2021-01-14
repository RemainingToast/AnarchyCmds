package org.auscpvp.cpvpcore.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {

    private static String prefix = "&8[&2&lCpvp&4&lCore&r&8]&r ";

    public static String getPrefix() {
        return prefix;
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

}
