package dev.hybridlabs.birds.utils;

import dev.hybridlabs.birds.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HBSpawnGroup {
    HYBRID_BIRDS_TERRESTRIAL_BIRD("terrestrial_bird", 6, true, false, 128),
    HYBRID_BIRDS_AQUATIC_BIRD("aquatic_bird", 6, true, false, 128);

    public MobCategory spawnGroup;
    public final String location;
    public final int spawnCap;
    public final boolean peaceful;
    public final boolean rare;
    public final int immediateDespawnRange;

    HBSpawnGroup(String id, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        this.location = Constants.MOD_ID + ":" + id;
        this.spawnCap = spawnCap;
        this.peaceful = peaceful;
        this.rare = rare;
        this.immediateDespawnRange = immediateDespawnRange;
    }

    public static final Map<String, MobCategory> BY_NAME = new ConcurrentHashMap<>();

    public static MobCategory byName(String name) {
        return BY_NAME.get(Constants.MOD_ID + ':' + name);
    }

}
