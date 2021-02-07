package com.github.anarchyplugins.anarchycore.hooks;

import com.github.anarchyplugins.anarchycore.AnarchyCore;
import com.github.anarchyplugins.anarchycore.utils.Util;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceholderExpansion extends me.clip.placeholderapi.expansion.PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "anarchycore";
    }

    @Override
    public @NotNull String getAuthor() {
        return "RemainingToast";
    }

    @Override
    public @NotNull String getVersion() {
        return AnarchyCore.INSTANCE.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if(params.equalsIgnoreCase("online")) {
            return String.valueOf(Util.countOnlinePlayers());
        }
        return null;
    }
}
