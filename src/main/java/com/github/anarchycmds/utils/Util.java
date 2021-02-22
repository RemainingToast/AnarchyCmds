package com.github.anarchycmds.utils;

import com.github.anarchycmds.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

    public static void vanishPlayer(Player vanishplayer) {
        Main.INSTANCE.gamemodelist.put(vanishplayer, vanishplayer.getGameMode());
        vanishplayer.setGameMode(GameMode.CREATIVE);

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.INSTANCE.getConfig().getString("messages.quit-message").replaceAll("%player%", vanishplayer.getDisplayName())));

        Main.INSTANCE.taskidlist.put(vanishplayer, Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.INSTANCE, () -> {
            TextComponent actionbar = new TextComponent("You are vanished at the moment!");
            actionbar.setColor(ChatColor.GOLD);

            vanishplayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, actionbar);
        }, 0, 20));

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != vanishplayer) {
                player.hidePlayer(Main.INSTANCE, vanishplayer);
            }
        }
    }

    public static void unvanishPlayer(Player unvanishplayer) {
        unvanishplayer.setGameMode(Main.INSTANCE.gamemodelist.get(unvanishplayer));
        Main.INSTANCE.gamemodelist.remove(unvanishplayer);

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.INSTANCE.getConfig().getString("messages.join-message").replaceAll("%player%", unvanishplayer.getDisplayName())));

        Bukkit.getScheduler().cancelTask(Main.INSTANCE.taskidlist.get(unvanishplayer));
        Main.INSTANCE.taskidlist.remove(unvanishplayer);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != unvanishplayer) {
                player.showPlayer(Main.INSTANCE, unvanishplayer);
            }
        }
    }

    public static int countOnlinePlayers(){
        int i = 0;
        for (Player p: Bukkit.getOnlinePlayers()){
            if(!Main.INSTANCE.isVanished(p)){
                i++;
            }
        }
        return i;
    }
}
