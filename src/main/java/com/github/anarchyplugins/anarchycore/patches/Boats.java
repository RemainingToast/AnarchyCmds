package com.github.anarchyplugins.anarchycore.patches;

import com.github.anarchyplugins.anarchycore.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Boat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/***
 * Code by moom0o msg me on discord if you want it removed
 */
public class Boats implements Listener {

    @EventHandler
    private void onMove(PlayerMoveEvent evt) {

        FileConfiguration config = Main.INSTANCE.getConfig();
        if (config.getBoolean("patch-boatfly")) {
            if (evt.getPlayer().isInsideVehicle()) {
                if (evt.getPlayer().getVehicle() instanceof Boat) {
                    int x = evt.getPlayer().getLocation().getBlockX();
                    int y = evt.getPlayer().getLocation().getBlockY();
                    int z = evt.getPlayer().getLocation().getBlockZ();

                    Location from = evt.getFrom();
                    Location to = evt.getTo();
                    double distY = to.getY() - from.getY();

                    double distX = to.getX() - from.getX();
                    double distZ = to.getZ() - from.getZ();
                    double finalValue = Math.hypot(distX, distZ);

                    if ((evt.getPlayer().getWorld().getBlockAt(x, y - 1, z).getType() == Material.AIR)
                        && evt.getPlayer().getWorld().getBlockAt(x, y - 2, z).getType() == Material.AIR
                        && evt.getPlayer().getWorld().getBlockAt(x, y - 3, z).getType() == Material.AIR) {

                        if (distY != -0.439999999105936) { // Minecraft has an exact Y distance number for boats
                            if (finalValue > 0.50 || distY > 0.0) {
                                evt.setCancelled(true);
                                evt.getPlayer().getVehicle().eject();
                            }
                        }
                    }
                }
            }
        }
    }
}
