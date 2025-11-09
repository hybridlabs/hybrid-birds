package dev.hybridlabs.birds.config

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.platform.Services
import dev.hybridlabs.birds.tag.HybridBirdsBiomeTags
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome

/**
 * Applies biome modifications for entities when initialised.
 */
@Suppress("SameParameterValue")
class EntitySpawnConfigGenerator {
    private val list: MutableList<EntitySpawnConfig> = mutableListOf()

    fun finalizeSpawn() {
        addBird(HybridBirdsEntityTypes.ROOSTER.get(), listOf(HybridBirdsBiomeTags.ROOSTER_SPAWN_BIOMES), 5, 1, 2)
        addBird(HybridBirdsEntityTypes.TURKEY.get(), listOf(HybridBirdsBiomeTags.TURKEY_SPAWN_BIOMES), 3, 1, 3)
        addBird(HybridBirdsEntityTypes.DUCK.get(), listOf(HybridBirdsBiomeTags.DUCK_SPAWN_BIOMES), 5, 1, 3)
        addBird(HybridBirdsEntityTypes.GOOSE.get(), listOf(HybridBirdsBiomeTags.GOOSE_SPAWN_BIOMES), 3, 1, 3)
        addBird(HybridBirdsEntityTypes.SWAN.get(), listOf(HybridBirdsBiomeTags.SWAN_SPAWN_BIOMES), 1, 1, 2)
        addBird(HybridBirdsEntityTypes.PEACOCK.get(), listOf(BiomeTags.IS_JUNGLE), 2, 1, 2)
        addBird(HybridBirdsEntityTypes.GUINEA_FOWL.get(), listOf(BiomeTags.IS_SAVANNA), 3, 1, 3)
    }

    private fun addBird(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("BIRD"),
            weight, minGroup, maxGroup)
    }

    private fun add(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        spawnGroup: MobCategory,
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
            generator.finalizeSpawn()
            return generator.list
        }
    }
}