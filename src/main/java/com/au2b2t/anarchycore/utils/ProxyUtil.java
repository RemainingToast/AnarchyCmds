package com.au2b2t.anarchycore.utils;

import com.au2b2t.anarchycore.AnarchyCore;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

public class ProxyUtil {

    AnarchyCore plugin;

    public ProxyUtil(AnarchyCore plugin) {
        this.plugin = plugin;
    }

    public boolean canConnect(String ip){
        List<String> ips = plugin.getConfig().getStringList("only-proxy-join.whitelist");
        return ips.contains(ip);
    }

    public boolean canConnect(InetAddress addr) {
        return this.canConnect(addr.getHostAddress());
    }

    public void whitelistIP(InetSocketAddress ip) {
        this.whitelistIP(ip.getAddress().getHostAddress());
    }

    public void whitelistIP(String ip) {
        plugin.getConfig().getStringList("only-proxy-join.whitelist").add(ip);
        plugin.saveConfig();
    }

}
