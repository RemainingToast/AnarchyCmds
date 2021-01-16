package org.auscpvp.cpvpcore.listeners.illegalchecks;

import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.ChatColor;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class HopperEvent implements Listener {

    CpvpCore plugin;

    public HopperEvent(CpvpCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryItemMove(InventoryMoveItemEvent event) {
        try {
            if (plugin.getConfig().getBoolean("illegal-items.checks.hopper")) {
                Inventory inv = event.getSource();
                if (inv.getContents() != null) {
                    for (ItemStack item : inv.getStorageContents()) {
                        if (item != null) {
                            if (plugin.getItemUtils().isArmor(item) || plugin.getItemUtils().isTool(item)) {
                                if (item.getDurability() > item.getType().getMaxDurability()) {
                                    item.setDurability(item.getType().getMaxDurability());
                                }
                                if (item.getDurability() < 0) {
                                    item.setDurability((short) 1);
                                }

                            }
                            if (plugin.getItemUtils().isIllegal(item)) {
                                inv.remove(item);
                                event.setCancelled(true);
                            }
                            if (plugin.getItemUtils().hasIllegalNBT(item)) {
                                inv.remove(item);
                                event.setCancelled(true);

                            }
                            if (plugin.getItemUtils().isOverstacked(item)) {
                                item.setAmount(item.getMaxStackSize());
                                event.setCancelled(true);
                            }
                            if (plugin.getItemUtils().hasIllegalEnchants(item)) {
                                inv.remove(item);
                                event.setCancelled(true);
                            }
                            if (item.hasItemMeta()) {
                                ItemMeta meta = item.getItemMeta();
                                if (meta.hasDisplayName()) {
                                    String name = ChatColor.stripColor(meta.getDisplayName());
                                    meta.setDisplayName(ChatColor.stripColor(meta.getDisplayName()));
                                    event.setCancelled(true);
                                    if (name.toCharArray().length > 35) {
                                        String newName = name.substring(0, 35);
                                        meta.setDisplayName(newName);
                                        event.setCancelled(true);
                                    }
                                    item.setItemMeta(meta);
                                }
                                if (plugin.getItemUtils().isEnchantedBlock(item)) {
                                    event.setCancelled(true);
                                }
                                if (item.getItemMeta() instanceof BlockStateMeta) {
                                    BlockStateMeta itemMeta = (BlockStateMeta) item.getItemMeta();
                                    if (itemMeta.getBlockState() instanceof ShulkerBox) {
                                        ShulkerBox shulker = (ShulkerBox) itemMeta.getBlockState();
                                        for (ItemStack shulkerItem : shulker.getInventory().getContents()) {
                                            if (shulkerItem != null) {
                                                if (plugin.getItemUtils().isArmor(item) || plugin.getItemUtils().isTool(item)) {
                                                    if (item.getDurability() > item.getType().getMaxDurability()) {
                                                        inv.remove(item);
                                                        event.setCancelled(true);
                                                    }
                                                    if (item.getDurability() < 0) {
                                                        inv.remove(item);
                                                        event.setCancelled(true);
                                                    }
                                                }
                                                if (plugin.getItemUtils().isIllegal(shulkerItem)) {
                                                    inv.remove(item);
                                                }
                                                if (plugin.getItemUtils().hasIllegalNBT(shulkerItem)) {
                                                    inv.remove(item);
                                                    event.setCancelled(true);
                                                }
                                                if (plugin.getItemUtils().isOverstacked(shulkerItem)) {
                                                    inv.remove(item);
                                                    event.setCancelled(true);
                                                }
                                                if (plugin.getItemUtils().hasIllegalEnchants(shulkerItem)) {
                                                    inv.remove(item);
                                                    event.setCancelled(true);
                                                }
                                            }
                                        }
                                    }
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
