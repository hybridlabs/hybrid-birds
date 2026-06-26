package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.utils.HBSpawnGroup
import net.minecraft.world.entity.MobCategory

object ForgeSpawnGroupRegistry {
    fun createHybridAquaticSpawnGroups() {
        // Extend the MobCategory enum with our spawn groups
        for (group in HBSpawnGroup.values()) {
            MobCategory.create(
                group.location.path,
                group.location.toString(),
                group.spawnCap,
                group.peaceful,
                group.rare,
                group.immediateDespawnRange
            )
        }
    }
}
