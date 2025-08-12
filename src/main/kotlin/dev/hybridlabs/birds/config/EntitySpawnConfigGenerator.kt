package dev.hybridlabs.birds.config

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome

/**
 * Applies biome modifications for entities when initialised.
 */
@Suppress("SameParameterValue")
class EntitySpawnConfigGenerator {
    private val list: MutableList<EntitySpawnConfig> = mutableListOf()

    fun initialize() {
        addBird(HybridBirdsEntityTypes.ROOSTER, listOf(BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE), 5, 1, 2)
        addBird(HybridBirdsEntityTypes.TURKEY, listOf(BiomeTags.IS_FOREST), 5, 1, 3)
        addBird(HybridBirdsEntityTypes.DUCK, listOf(BiomeTags.IS_RIVER), 5, 1, 3)
        addBird(HybridBirdsEntityTypes.GOOSE, listOf(BiomeTags.IS_RIVER), 5, 1, 3)
        addBird(HybridBirdsEntityTypes.SWAN, listOf(BiomeTags.IS_RIVER), 5, 1, 2)
        addBird(HybridBirdsEntityTypes.PEACOCK, listOf(BiomeTags.IS_JUNGLE), 5, 1, 2)
        addBird(HybridBirdsEntityTypes.GUINEA_FOWL, listOf(BiomeTags.IS_SAVANNA), 5, 1, 3)
    }

    private fun addBird(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTags, SpawnGroup.CREATURE, weight, minGroup, maxGroup)
    }

    private fun add(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        spawnGroup: SpawnGroup,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        spawnTags.forEach { spawnTag ->
            list.add(EntitySpawnConfig(entityType, spawnTag, spawnGroup, weight, minGroup, maxGroup))
        }
    }

    companion object {
        fun generate(): List<EntitySpawnConfig> {
            val generator = EntitySpawnConfigGenerator()
            generator.initialize()
            return generator.list
        }
    }
}