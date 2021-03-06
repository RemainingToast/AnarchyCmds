package com.github.anarchycmds;

import com.github.anarchycmds.commands.*;
import com.github.anarchycmds.utils.ConnectionEvents;
import com.github.anarchycmds.utils.PlaceholderExpansion;
import com.github.anarchycmds.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    public final HashMap<Player, GameMode> gamemodelist = new HashMap<>();
    public final HashMap<Player, Integer> taskidlist = new HashMap<>();

    private final PluginManager pluginManager = getServer().getPluginManager();
    public static File dataFolder;
    public static Main INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        dataFolder = this.getDataFolder();

        Util.setPrefix(getConfig().getString("prefix"));

        pluginManager.registerEvents(new ConnectionEvents(), this);

        getCommand("anarchycmds").setExecutor(new CoreCmd());
        getCommand("discord").setExecutor(new Discord());
        getCommand("gmc").setExecutor(new GMC());
        getCommand("gms").setExecutor(new GMS());
        getCommand("gmsp").setExecutor(new GMSP());
        getCommand("vote").setExecutor(new Vote());
        getCommand("help").setExecutor(new Help());
        getCommand("kill").setExecutor(new Kill());
        getCommand("toggleconnectionmsgs").setExecutor(new ToggleConnectionMsgs());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("playtime").setExecutor(new Playtime());

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PlaceholderExpansion().register();
        }
    }

    public boolean isVanished(Player player) {
        return gamemodelist.containsKey(player) && taskidlist.containsKey(player);
    }

}
