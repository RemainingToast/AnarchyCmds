package org.auscpvp.cpvpcore;

import io.papermc.lib.PaperLib;
import org.auscpvp.cpvpcore.commands.*;
import org.auscpvp.cpvpcore.listeners.ConnectionEvents;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CpvpCore extends JavaPlugin {

    private final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        if(!PaperLib.isPaper()){
            PaperLib.suggestPaper(this);
        }
        saveDefaultConfig();

        pluginManager.registerEvents(new ConnectionEvents(this), this);

        getCommand("toggleconnectionmsgs").setExecutor(new ConnectionEvents(this));
        getCommand("kill").setExecutor(new KillCmd(this));
        getCommand("spawn").setExecutor(new SpawnCmd(this));
        getCommand("gmc").setExecutor(new GmcCmd(this));
        getCommand("gms").setExecutor(new GmsCmd(this));
        getCommand("gmsp").setExecutor(new GmspCmd(this));
        getCommand("help").setExecutor(new HelpCmd(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
