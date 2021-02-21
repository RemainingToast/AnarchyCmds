package com.github.anarchyplugins.anarchycmds.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Playtime implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if (args.length > 0){
                Player p2 = Bukkit.getPlayer(args[0]);
                if (p2 != null)  sendPlaytimeMessage(p, p2);
                else p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c\"" + args[0] + "\" is offline or not a player!"));
            } else sendPlaytimeMessage(p, null);
        } else if(sender instanceof ConsoleCommandSender){
            if(args.length > 0){
                Player p2 = Bukkit.getPlayer(args[0]);
                if (p2 != null)  sendPlaytimeMessage(sender, p2);
                else sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c\"" + args[0] + "\" is offline or not a player!"));
            }
        }
        return true;
    }

    private static void sendPlaytimeMessage(CommandSender sender, Player player2){
        Player player = null;
        if(sender instanceof Player) player = (Player) sender;
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6-----------------------------------------------------"));
        if(player2!=null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3" + player2.getDisplayName() + " first joined " + getFormattedDate(player2)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3" + player2.getDisplayName() + "'s playtime is " + getFormattedTime(player2.getStatistic(Statistic.PLAY_ONE_TICK))));
        } else if(player!=null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3You first joined " + getFormattedDate(player)));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Your playtime is " + getFormattedTime(player.getStatistic(Statistic.PLAY_ONE_TICK))));
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6-----------------------------------------------------"));
    }

    private static String getFormattedDate(Player player){
        Date date = new Date(player.getFirstPlayed());
        return "&a" + new SimpleDateFormat("E, MMM dd yyyy").format(date);
    }


    private static String getFormattedTime(int i) {
        int ticks = i / 20;
        int days = (int) TimeUnit.SECONDS.toDays(ticks);
        int hours = (int) (TimeUnit.SECONDS.toHours(ticks) - TimeUnit.DAYS.toHours(days));
        int minutes = (int) (TimeUnit.SECONDS.toMinutes(ticks) - TimeUnit.HOURS.toMinutes(hours)
                - TimeUnit.DAYS.toMinutes(days));
        int seconds = (int) (TimeUnit.SECONDS.toSeconds(ticks) - TimeUnit.MINUTES.toSeconds(minutes)
                - TimeUnit.HOURS.toSeconds(hours) - TimeUnit.DAYS.toSeconds(days));

        if (days != 0) {
            return "&a" + days + "&3 days, &a" + hours + "&3 hours, &a" + minutes + "&3 mins, &a" + seconds + "&3 secs";
        } else {
            if (hours != 0) {
                return "&a" + hours + "&3 hours, &a" + minutes + "&3 minutes, &a" + seconds + "&3 seconds";
            } else {
                if (minutes != 0) {
                    return "&a" + minutes + "&3 minutes, &a" + seconds + "&3 seconds";
                } else {
                    return "&a" + seconds + "&3 seconds";
                }
            }
        }
    }
}
