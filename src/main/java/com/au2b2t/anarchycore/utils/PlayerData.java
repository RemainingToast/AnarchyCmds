package com.au2b2t.anarchycore.utils;

import com.au2b2t.anarchycore.AnarchyCore;
import com.google.common.reflect.TypeToken;
import com.google.gson.annotations.SerializedName;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static com.au2b2t.anarchycore.utils.PlayerDataUtilities.playerDataFolder;

/***
 * @author willemml
 */
public class PlayerData {
    @SerializedName("uuid")
    private UUID uuid;
    @SerializedName("ignored")
    private ArrayList<UUID> ignored = new ArrayList<>();

    public PlayerData(OfflinePlayer player) {
        this.uuid = player.getUniqueId();
    }

    public UUID getUuid() {
        return uuid;
    }

    public ArrayList<UUID> getIgnored() {
        return ignored;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "uuid=" + uuid +
                ", ignored=" + ignored +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, ignored);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerData)) return false;
        PlayerData that = (PlayerData) o;
        return this.uuid == that.uuid;
    }

    public boolean hasIgnored(UUID player) {
        if (ignored == null) ignored = new ArrayList<>();
        return ignored.contains(player);
    }

    public Player getPlayer() {
        return AnarchyCore.INSTANCE.getServer().getPlayer(uuid);
    }

    public PlayerData setUuidIfNull(UUID uuid) {
        if (this.uuid == null) {
            this.uuid = uuid;
        }
        return this;
    }

    public PlayerData toggleIgnore(UUID player) {
        if (ignored == null) ignored = new ArrayList<>();
        if (ignored.contains(player)) ignored.remove(player);
        else ignored.add(player);
        return this;
    }

    public PlayerData save() {
        PlayerDataUtilities.playerData.remove(uuid);
        PlayerDataUtilities.playerData.putIfAbsent(uuid, this);
        File playerDataFile = new File(playerDataFolder + uuid.toString() + ".json");
        playerDataFile.getParentFile().mkdirs();
        try {
            playerDataFile.createNewFile();
            FileWriter fw = new FileWriter(playerDataFile);
            fw.write(AnarchyCore.gson.toJson(this));
            fw.flush();
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to save player data for " + uuid.toString() + ", error:");
            e.printStackTrace();
        }
        return this;
    }

    public PlayerData load() {
        File playerDataFile = new File(playerDataFolder + uuid + ".json");
        playerDataFile.getParentFile().mkdirs();
        try {
            return AnarchyCore.gson.fromJson(new FileReader(playerDataFile), new TypeToken<PlayerData>() {
            }.getType());
        } catch (FileNotFoundException e) {
            return this.save();
        }
    }
}
