package org.auscpvp.cpvpcore.listeners.illegalchecks;

import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class PickupEvent implements Listener {

    CpvpCore plugin;

    public PickupEvent(CpvpCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPickup(PlayerAttemptPickupItemEvent event) {
        try {
            if (plugin.getConfig().getBoolean("illegal-items.checks.pickup")) {
                ItemStack item = event.getItem().getItemStack();
                if (plugin.getItemUtils().isEnchantedBlock(item) || plugin.getItemUtils().hasIllegalNBT(item) || plugin.getItemUtils().hasIllegalEnchants(item)
                        || plugin.getItemUtils().isOverstacked(item) || plugin.getItemUtils().isIllegal(item)) {
                    event.setCancelled(true);
                    event.getItem().remove();
                }
                if (item.getItemMeta() instanceof BlockStateMeta) {
                    BlockStateMeta itemMeta = (BlockStateMeta) item.getItemMeta();
                    if (itemMeta.getBlockState() instanceof ShulkerBox) {
                        ShulkerBox box = (ShulkerBox) itemMeta.getBlockState();
                        for (ItemStack shulkerItem : box.getInventory().getContents()) {
                            if (shulkerItem != null) {
                                if (plugin.getItemUtils().isArmor(item) || plugin.getItemUtils().isTool(item)) {
                                    if (item.getDurability() > item.getType().getMaxDurability()) {
                                        event.getItem().remove();
                                        event.setCancelled(true);
                                    }
                                    if (item.getDurability() < 0) {
                                        event.getItem().remove();
                                        event.setCancelled(true);
                                    }
                                }
                                if (plugin.getItemUtils().isIllegal(shulkerItem)) {
                                    event.getItem().remove();
                                }
                                if (plugin.getItemUtils().hasIllegalNBT(shulkerItem)) {
                                    event.getItem().remove();
                                    event.setCancelled(true);

                                }
                                if (plugin.getItemUtils().isOverstacked(shulkerItem)) {
                                    event.getItem().remove();
                                    event.setCancelled(true);
                                }
                                if (plugin.getItemUtils().hasIllegalEnchants(shulkerItem)) {
                                    event.getItem().remove();
                                    event.setCancelled(true);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Error | Exception throwable) {
            System.out.println(throwable.toString());
        }
    }

}
