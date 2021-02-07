package com.github.anarchyplugins.anarchycore.patches;

import com.github.anarchyplugins.anarchycore.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Nether implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(Main.INSTANCE.getConfig().getBoolean("disable-netherroof")){
            Player p = e.getPlayer();
            if(p.isOp()) return;
            int x = p.getLocation().getBlockX();
            int z = p.getLocation().getBlockZ();
            Location setBackLocation = new Location(p.getWorld(), z, 120, x).toCenterLocation();
            setBackLocation.getBlock().setType(Material.AIR);
            setBackLocation.getBlock().getRelative(BlockFace.DOWN).setType(Material.AIR);
            setBackLocation.getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
            if(p.getWorld().getEnvironment() == World.Environment.NETHER && p.getLocation().getBlockY() >= 127){
                p.teleport(setBackLocation);
            }
        }
    }
}
