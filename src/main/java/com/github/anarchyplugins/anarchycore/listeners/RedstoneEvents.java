package com.github.anarchyplugins.anarchycore.listeners;

import com.github.anarchyplugins.anarchycore.AnarchyCore;
import com.github.anarchyplugins.anarchycore.utils.EveryTenSecondsEvent;
import com.github.anarchyplugins.anarchycore.utils.Util;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class RedstoneEvents implements Listener {

    private final HashMap<Player, Integer> leverHashMap = new HashMap<>();

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

    @EventHandler
    public void onPull(PlayerInteractEvent event) {
        try {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getClickedBlock().getType() == Material.LEVER) {
                    Player player = event.getPlayer();
                    if (leverHashMap.containsKey(player)) {
                        leverHashMap.put(player, leverHashMap.get(player) + 1);
                    } else {
                        leverHashMap.put(player, 1);
                    }
                    if (leverHashMap.get(player) > 5) {
                        event.setCancelled(true);
                        player.kickPlayer("");
                        leverHashMap.remove(player);
                    }
                }
            }
        } catch (Error | Exception throwable) {
            System.out.println(throwable);
        }
    }

    @EventHandler
    public void onTenSeconds(EveryTenSecondsEvent event){
        leverHashMap.clear();
    }

}
