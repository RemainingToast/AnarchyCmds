package com.github.anarchyplugins.anarchycore.patches;

import com.github.anarchyplugins.anarchycore.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;

public class Mobs {

    public void enable(){
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(Main.INSTANCE, () ->{
            Bukkit.getServer().getScheduler().runTaskAsynchronously(Main.INSTANCE, () ->{
                for (World world : Bukkit.getWorlds()){
                    for (Entity e : world.getEntities()){
                        if(e instanceof LivingEntity){
                            LivingEntity entity = (LivingEntity) e;
                            if(shouldLimitEntity(entity)){
                                entity.setAI(false);
                                if(Main.INSTANCE.getConfig().getBoolean("debug")){
                                    System.out.println("Disabled entity: " + entity + "'s AI at: " + entity.getLocation());
                                }
                            } else entity.setAI(true);
                        }
                    }
                }
            });
        }, 0L, 100L);
    }


    private boolean shouldLimitEntity(LivingEntity entity) {
        if(!Main.INSTANCE.getConfig().getBoolean("limit-mob-ai")) return false;
        switch (entity.getType()) {
            case BAT:
            case RABBIT:
            case POLAR_BEAR:
            case ENDERMITE:
            case ARMOR_STAND:
                return true;
            default:
        }
        // getCustomName() = NameTag
        if(entity.getCustomName() != null
                || entity instanceof Wolf
                || entity instanceof Parrot
                || entity.fromMobSpawner() && getPlayerCount() < 110) {
            return false;
        } else if(entity instanceof Skeleton) {
            return isEntityNearPlayer(entity, 10);
        } else if(!isEntityNearPlayer(entity, 40)) {
            return true;
        } else if(entity instanceof Monster) {
            return getTps() < 10 || getPlayerCount() > 90;
        } else {
            return getTps() < 6 || getPlayerCount() > 120;
        }
    }

    private int getPlayerCount() {
        return Bukkit.getServer().getOnlinePlayers().size();
    }

    private int getTps() {
        return (int) Bukkit.getServer().getTPS()[0];
    }

    private boolean isEntityNearPlayer(LivingEntity entity, double distance) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.getNearbyEntities(distance, distance, distance).contains(entity)) {
                return true;
            }
        }
        return false;
    }

}
