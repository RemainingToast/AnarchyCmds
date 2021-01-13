package org.auscpvp.cpvpcore;

import org.auscpvp.cpvpcore.commands.KillCommand;
import org.auscpvp.cpvpcore.commands.SpawnCommand;
import org.auscpvp.cpvpcore.listeners.ConnectionEvents;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CpvpCore extends JavaPlugin {

    private final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        pluginManager.registerEvents(new ConnectionEvents(this), this);

        //Commands | A-Z
        getCommand("kill").setExecutor(new KillCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
