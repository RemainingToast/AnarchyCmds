package com.github.anarchyplugins.anarchycore.listeners;

import com.github.anarchyplugins.anarchycore.AnarchyCore;
import com.github.anarchyplugins.anarchycore.utils.Util;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class RedstoneEvents implements Listener {

    @EventHandler
    public void onRedstone(BlockRedstoneEvent e){
        double configTps = AnarchyCore.INSTANCE.getConfig().getInt("disable-redstone-tps");
        double entityTps = AnarchyCore.INSTANCE.getConfig().getInt("delete-entities-tps");
        if(configTps == -1) return;
        if(Util.getTps() <= configTps){
            e.setNewCurrent(0);
            if(Util.getTps() <= entityTps){
                for (Entity entity : e.getBlock().getChunk().getEntities()) {
                    if (!(entity instanceof Player)) {
                        entity.remove();
                    }
                }
            }
        }
    }
}
