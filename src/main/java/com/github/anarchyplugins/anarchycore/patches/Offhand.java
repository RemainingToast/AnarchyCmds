package com.github.anarchyplugins.anarchycore.patches;

import com.github.anarchyplugins.anarchycore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import java.util.HashMap;

public class Offhand implements Listener {

    public static HashMap<Player, Long> lastChange = new HashMap<>();
    public static HashMap<Player, Integer> warnings = new HashMap<>();

    @EventHandler
    public void onMainHandChange(PlayerSwapHandItemsEvent event) {
        FileConfiguration config = Main.INSTANCE.getConfig();
        if(config.getBoolean("patch-offhand-crash")){
            if (lastChange.get(event.getPlayer()) != null && lastChange.get(event.getPlayer()) + 250 > System.currentTimeMillis()) {
                warnings.put(event.getPlayer(), warnings.get(event.getPlayer()) + 1);
                if(config.getBoolean("debug")){
                    System.out.println(event.getPlayer().getDisplayName() + " is performing offhand crash. (" + warnings.get(event.getPlayer()) + "/5)");
                }
                if (warnings.get(event.getPlayer()) > 4) {
                    event.getPlayer().kickPlayer("");
                    warnings.put(event.getPlayer(), 0);
                }
            }
            lastChange.put(event.getPlayer(), System.currentTimeMillis());
        }
    }
}
