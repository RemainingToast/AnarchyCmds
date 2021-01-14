package org.auscpvp.cpvpcore.listeners.illegalchecks;

import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChunkLoadEvent implements Listener {
    CpvpCore plugin;

    public ChunkLoadEvent(CpvpCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLoad(org.bukkit.event.world.ChunkLoadEvent event) {
        try {
            if (plugin.getConfig().getBoolean("illegal-items.checks.chunk-load")) {
                for (BlockState state : event.getChunk().getTileEntities()) {
                    if (state instanceof Container) {
                        Container container = (Container) state;
                        plugin.getItemUtils().deleteIllegals(container.getInventory());
                    }
                }
            }
        } catch (Error | Exception throwable) {
            System.out.println(throwable.toString());
        }
    }
}
