package com.github.anarchyplugins.anarchycore.utils;

import com.github.anarchyplugins.anarchycore.AnarchyCore;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.logging.Logger;

public class EveryTenSecondsEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Logger logger;
    private final AnarchyCore plugin;

    public EveryTenSecondsEvent(Logger logger, AnarchyCore main) {
        this.logger = logger;
        plugin = main;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Logger getLogger() {
        return logger;
    }

    public AnarchyCore getPlugin() {
        return plugin;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
