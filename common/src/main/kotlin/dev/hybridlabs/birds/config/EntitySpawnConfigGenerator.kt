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
        addLandBird(HybridBirdsEntityTypes.ROOSTER.get(), listOf(HybridBirdsBiomeTags.ROOSTER_SPAWN_BIOMES), 5, 1, 2)
        addLandBird(HybridBirdsEntityTypes.TURKEY.get(), listOf(HybridBirdsBiomeTags.TURKEY_SPAWN_BIOMES), 3, 1, 3)
        addLandBird(HybridBirdsEntityTypes.PEACOCK.get(), listOf(BiomeTags.IS_JUNGLE), 2, 1, 2)
        addLandBird(HybridBirdsEntityTypes.GUINEA_FOWL.get(), listOf(BiomeTags.IS_SAVANNA), 3, 1, 3)
        addLandBird(HybridBirdsEntityTypes.HUMMINGBIRD.get(), listOf(HybridBirdsBiomeTags.HUMMINGBIRD_SPAWN_BIOMES), 3, 1, 3)
        addLandBird(HybridBirdsEntityTypes.OSTRICH.get(), listOf(BiomeTags.IS_SAVANNA), 2, 1, 3)
        addLandBird(HybridBirdsEntityTypes.KIWI.get(), listOf(HybridBirdsBiomeTags.KIWI_SPAWN_BIOMES), 2, 1, 1)
        addLandBird(HybridBirdsEntityTypes.JAY.get(), listOf(BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA), 3, 1, 3)
        addWaterBird(HybridBirdsEntityTypes.SEAGULL.get(), listOf(BiomeTags.IS_BEACH, BiomeTags.IS_OCEAN), 3, 2, 5)
        addWaterBird(HybridBirdsEntityTypes.PELICAN.get(), listOf(BiomeTags.IS_BEACH, BiomeTags.IS_OCEAN), 1, 1, 2)
        addWaterBird(HybridBirdsEntityTypes.FLAMINGO.get(), listOf(HybridBirdsBiomeTags.FLAMINGO_SPAWN_BIOMES), 2, 1, 3)
        addWaterBird(HybridBirdsEntityTypes.DUCK.get(), listOf(HybridBirdsBiomeTags.DUCK_SPAWN_BIOMES), 5, 1, 3)
        addWaterBird(HybridBirdsEntityTypes.GOOSE.get(), listOf(HybridBirdsBiomeTags.GOOSE_SPAWN_BIOMES), 3, 1, 3)
        addWaterBird(HybridBirdsEntityTypes.SWAN.get(), listOf(HybridBirdsBiomeTags.SWAN_SPAWN_BIOMES), 1, 1, 2)
    }

    private fun addLandBird(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HB_LAND_BIRD"),
            weight, minGroup, maxGroup)
    }

    private fun addWaterBird(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HB_WATER_BIRD"),
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