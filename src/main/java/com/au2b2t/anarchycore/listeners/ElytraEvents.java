package com.au2b2t.anarchycore.listeners;

import com.au2b2t.anarchycore.AnarchyCore;
import com.au2b2t.anarchycore.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

public class ElytraEvents implements Listener {

    @EventHandler
    public void onElytra(EntityToggleGlideEvent e){
        if(e.getEntity() instanceof Player){
            if(Util.getTps() <= AnarchyCore.INSTANCE.getConfig().getInt("disable-elytra-tps")){
                e.setCancelled(true);
            }
        }
    }
}
