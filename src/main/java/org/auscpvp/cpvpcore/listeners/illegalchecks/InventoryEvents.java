package org.auscpvp.cpvpcore.listeners.illegalchecks;

import org.auscpvp.cpvpcore.CpvpCore;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class InventoryEvents implements Listener {

    CpvpCore plugin;
    public InventoryEvents(CpvpCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        try {
            if (plugin.getConfig().getBoolean("illegal-items.checks.inventory")) {
                Inventory inv = event.getInventory();
                plugin.getItemUtils().deleteIllegals(inv);
                Inventory playerInv = event.getPlayer().getInventory();
                plugin.getItemUtils().deleteIllegals(playerInv);
                if (event.getInventory().getType() == InventoryType.SHULKER_BOX) {
                    Inventory shulkerInv = event.getInventory();
                    for (ItemStack item : shulkerInv.getContents()) {
                        if (item != null) {
                            if (item.getItemMeta() instanceof BlockStateMeta) {
                                BlockStateMeta blockStateMeta = (BlockStateMeta) item.getItemMeta();
                                if (blockStateMeta.getBlockState() instanceof ShulkerBox) {
                                    shulkerInv.remove(item);
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

    @EventHandler
    public void onInventoryClose(InventoryOpenEvent event) {
        try {
            if (plugin.getConfig().getBoolean("illegal-items.checks.inventory")) {
                Inventory inv = event.getInventory();
                plugin.getItemUtils().deleteIllegals(inv);
            }
        } catch (Error | Exception throwable) {
            System.out.println(throwable.toString());
        }
    }
}
