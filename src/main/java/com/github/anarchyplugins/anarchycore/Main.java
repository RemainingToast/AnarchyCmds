package com.github.anarchyplugins.anarchycore;

import com.github.anarchyplugins.anarchycore.patches.Boats;
import com.github.anarchyplugins.anarchycore.patches.Mobs;
import com.github.anarchyplugins.anarchycore.utils.PlaceholderExpansion;
import com.github.anarchyplugins.anarchycore.patches.AntiLag;
import com.github.anarchyplugins.anarchycore.utils.ConnectionEvents;
import com.github.anarchyplugins.anarchycore.utils.EveryTenSecondsEvent;
import com.github.anarchyplugins.anarchycore.utils.Util;
import com.github.anarchyplugins.anarchycore.commands.*;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Main extends JavaPlugin {

    public final HashMap<Player, GameMode> gamemodelist = new HashMap<>();
    public final HashMap<Player, Integer> taskidlist = new HashMap<>();

    private final PluginManager pluginManager = getServer().getPluginManager();
    public static File dataFolder;
    public static Gson gson = new Gson();
    public static Main INSTANCE;

    EveryTenSecondsEvent tenSecondPassEvent = new EveryTenSecondsEvent(getLogger(), this);

    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        dataFolder = this.getDataFolder();

        Util.setPrefix(getConfig().getString("prefix"));

        pluginManager.registerEvents(new AntiLag(), this);
        pluginManager.registerEvents(new Boats(), this);

        pluginManager.registerEvents(new ConnectionEvents(), this);

        getCommand("anarchycore").setExecutor(new AnarchyCore());
        getCommand("discord").setExecutor(new Discord());
        getCommand("gmc").setExecutor(new GMC());
        getCommand("gms").setExecutor(new GMS());
        getCommand("gmsp").setExecutor(new GMSP());
        getCommand("help").setExecutor(new Help());
        getCommand("kill").setExecutor(new Kill());
        getCommand("toggleconnectionmsgs").setExecutor(new ToggleConnectionMsgs());
        getCommand("vanish").setExecutor(new Vanish());

        if(getConfig().getBoolean("limit-mob-ai")) new Mobs().enable();

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PlaceholderExpansion().register();
        }

        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(() -> pluginManager.callEvent(tenSecondPassEvent), 1, 10, TimeUnit.SECONDS);
    }

    public boolean isVanished(Player player) {
        return gamemodelist.containsKey(player) && taskidlist.containsKey(player);
    }

}
