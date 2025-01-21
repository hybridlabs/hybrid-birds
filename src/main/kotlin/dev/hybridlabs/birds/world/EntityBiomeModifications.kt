@file:Suppress("SameParameterValue")

package dev.hybridlabs.birds.world

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.utils.HybridBirdsSpawnGroup
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome

object EntityBiomeModifications {
    init {
        addBird(HybridBirdsEntityTypes.ROOSTER, BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE, 5, 1, 2)
        addBird(HybridBirdsEntityTypes.DUCK, BiomeTags.IS_RIVER, 5, 1, 2)
        addBird(HybridBirdsEntityTypes.GOOSE, BiomeTags.IS_RIVER, 5, 1, 2)
        addBird(HybridBirdsEntityTypes.SWAN, BiomeTags.IS_RIVER, 5, 1, 2)
        addBird(HybridBirdsEntityTypes.TURKEY, BiomeTags.IS_FOREST, 5, 1, 2)
    }

    private fun addBird(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridBirdsSpawnGroup.BIRDS.spawnGroup, weight, minGroup, maxGroup)
    }

    private fun add(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        spawnGroup: SpawnGroup,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        BiomeModifications.addSpawn({ it.hasTag(spawnTag) }, spawnGroup, entityType, weight, minGroup, maxGroup)
    }
}