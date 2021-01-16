package org.auscpvp.cpvpcore;

import org.auscpvp.cpvpcore.commands.*;
import org.auscpvp.cpvpcore.listeners.ConnectionEvents;
import org.auscpvp.cpvpcore.listeners.illegalchecks.ChunkLoadEvent;
import org.auscpvp.cpvpcore.listeners.illegalchecks.HopperEvent;
import org.auscpvp.cpvpcore.listeners.illegalchecks.InventoryEvents;
import org.auscpvp.cpvpcore.listeners.illegalchecks.PickupEvent;
import org.auscpvp.cpvpcore.utils.ItemUtil;
import org.auscpvp.cpvpcore.utils.Util;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

public final class CpvpCore extends JavaPlugin {

    ConnectionEvents connectionEvents = new ConnectionEvents(this);
    private final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Util.setPrefix(getConfig().getString("prefix"));

        pluginManager.registerEvents(new ConnectionEvents(this), this);
        pluginManager.registerEvents(new ChunkLoadEvent(this), this);
        pluginManager.registerEvents(new PickupEvent(this), this);
        pluginManager.registerEvents(new InventoryEvents(this), this);
        pluginManager.registerEvents(new HopperEvent(this), this);

        getCommand("toggleconnectionmsgs").setExecutor(new ToggleConnectionMsgsCmd(this));
  
        getCommand("kill").setExecutor(new KillCmd(this));
        getCommand("spawn").setExecutor(new SpawnCmd(this));
        getCommand("gmc").setExecutor(new GmcCmd(this));
        getCommand("gms").setExecutor(new GmsCmd(this));
        getCommand("gmsp").setExecutor(new GmspCmd(this));
        getCommand("help").setExecutor(new HelpCmd(this));
        getCommand("cpvpcore").setExecutor(new CoreCmd(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ItemUtil getItemUtils(){
        return new ItemUtil(this);
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
