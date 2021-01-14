package org.auscpvp.cpvpcore;

import org.auscpvp.cpvpcore.commands.*;
import org.auscpvp.cpvpcore.listeners.ConnectionEvents;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

public final class CpvpCore extends JavaPlugin {

    private final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
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

    public boolean canConnect(String ip){
        List<String> ips = getConfig().getStringList("only-proxy-join.whitelist");
        return ips.contains(ip);
    }

    public boolean canConnect(InetAddress addr) {
        return this.canConnect(addr.getHostAddress());
    }

    public void whitelistIP(InetSocketAddress ip) {
        this.whitelistIP(ip.getAddress().getHostAddress());
    }

    public void whitelistIP(String ip) {
        getConfig().getStringList("only-proxy-join.whitelist").add(ip);
        saveConfig();
    }
}
