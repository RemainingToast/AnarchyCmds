package com.github.anarchyplugins.anarchycore;

import com.github.anarchyplugins.anarchycore.hooks.PlaceholderExpansion;
import com.github.anarchyplugins.anarchycore.utils.EveryTenSecondsEvent;
import com.github.anarchyplugins.anarchycore.utils.ItemUtil;
import com.github.anarchyplugins.anarchycore.utils.Util;
import com.github.anarchyplugins.anarchycore.commands.*;
import com.github.anarchyplugins.anarchycore.listeners.*;
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

public final class AnarchyCore extends JavaPlugin {

    public final HashMap<Player, GameMode> gamemodelist = new HashMap<>();
    public final HashMap<Player, Integer> taskidlist = new HashMap<>();

    private final PluginManager pluginManager = getServer().getPluginManager();
    public static File dataFolder;
    public static Gson gson = new Gson();
    public static AnarchyCore INSTANCE;

    EveryTenSecondsEvent tenSecondPassEvent = new EveryTenSecondsEvent(getLogger(), this);

    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        dataFolder = this.getDataFolder();

        Util.setPrefix(getConfig().getString("prefix"));

        pluginManager.registerEvents(new RedstoneEvents(),this);
        pluginManager.registerEvents(new ElytraEvents(), this);
        pluginManager.registerEvents(new MoveEvents(), this);
        pluginManager.registerEvents(new ConnectionEvents(this), this);

        getCommand("toggleconnectionmsgs").setExecutor(new ToggleConnectionMsgsCmd(this));
        getCommand("kill").setExecutor(new KillCmd(this));
        getCommand("discord").setExecutor(new DiscordCmd(this));
        getCommand("gmc").setExecutor(new GmcCmd(this));
        getCommand("gms").setExecutor(new GmsCmd(this));
        getCommand("gmsp").setExecutor(new GmspCmd(this));
        getCommand("help").setExecutor(new HelpCmd(this));
        getCommand("vanish").setExecutor(new VanishCmd());
        getCommand("anarchycore").setExecutor(new CoreCmd(this));

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PlaceholderExpansion().register();
        }

        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(() -> pluginManager.callEvent(tenSecondPassEvent), 1, 10, TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ItemUtil getItemUtils(){
        return new ItemUtil(this);
    }

    public boolean isVanished(Player player) {
        return gamemodelist.containsKey(player) && taskidlist.containsKey(player);
    }

}
