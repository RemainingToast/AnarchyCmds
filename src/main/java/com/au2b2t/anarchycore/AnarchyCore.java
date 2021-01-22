package com.au2b2t.anarchycore;

import com.au2b2t.anarchycore.commands.*;
import com.au2b2t.anarchycore.listeners.ConnectionEvents;
import com.au2b2t.anarchycore.listeners.illegalchecks.ChunkLoadEvent;
import com.au2b2t.anarchycore.listeners.illegalchecks.HopperEvent;
import com.au2b2t.anarchycore.listeners.illegalchecks.InventoryEvents;
import com.au2b2t.anarchycore.listeners.illegalchecks.PickupEvent;
import com.au2b2t.anarchycore.utils.ItemUtil;
import com.au2b2t.anarchycore.utils.Util;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AnarchyCore extends JavaPlugin {

    private final PluginManager pluginManager = getServer().getPluginManager();
    public static File dataFolder;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        dataFolder = this.getDataFolder();

        Util.setPrefix(getConfig().getString("prefix"));

        pluginManager.registerEvents(new ConnectionEvents(this), this);
        pluginManager.registerEvents(new ChunkLoadEvent(this), this);
        pluginManager.registerEvents(new PickupEvent(this), this);
        pluginManager.registerEvents(new InventoryEvents(this), this);
        pluginManager.registerEvents(new HopperEvent(this), this);

        getCommand("toggleconnectionmsgs").setExecutor(new ToggleConnectionMsgsCmd(this));
        getCommand("kill").setExecutor(new KillCmd(this));
        getCommand("discord").setExecutor(new DiscordCmd(this));
        getCommand("gmc").setExecutor(new GmcCmd(this));
        getCommand("gms").setExecutor(new GmsCmd(this));
        getCommand("gmsp").setExecutor(new GmspCmd(this));
        getCommand("help").setExecutor(new HelpCmd(this));
        getCommand("anarchycore").setExecutor(new CoreCmd(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ItemUtil getItemUtils(){
        return new ItemUtil(this);
    }

    public ProxyUtil getProxyUtils() { return new ProxyUtil(this); }


}
