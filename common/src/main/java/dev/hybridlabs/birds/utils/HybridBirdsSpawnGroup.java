package dev.hybridlabs.birds.utils;

import net.minecraft.world.entity.MobCategory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HybridBirdsSpawnGroup {
    HB_LAND_BIRD("HB_LAND_BIRD", 6, true, false, 128),
    HB_WATER_BIRD("HB_WATER_BIRD", 6, true, false, 128);

    public MobCategory spawnGroup;
    public final String name;
    public final int spawnCap;
    public final boolean peaceful;
    public final boolean rare;
    public final int immediateDespawnRange;

    HybridBirdsSpawnGroup(String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        this.name = name;
        this.spawnCap = spawnCap;
        this.peaceful = peaceful;
        this.rare = rare;
        this.immediateDespawnRange = immediateDespawnRange;
    }

    public static final Map<String, MobCategory> BY_NAME = new ConcurrentHashMap<>();

    public static MobCategory byName(String name) {
        return BY_NAME.get(name);
    }
}
