package dev.hybridlabs.skies.config

import dev.hybridlabs.skies.entity.HSEntityTypes
import dev.hybridlabs.skies.platform.Services
import dev.hybridlabs.skies.tag.HSBiomeTags
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
        addLandBird(HSEntityTypes.ROOSTER.get(), listOf(HSBiomeTags.ROOSTER_SPAWN_BIOMES), 5, 1, 2)
        addLandBird(HSEntityTypes.TURKEY.get(), listOf(HSBiomeTags.TURKEY_SPAWN_BIOMES), 3, 1, 3)
        addLandBird(HSEntityTypes.PEACOCK.get(), listOf(BiomeTags.IS_JUNGLE), 2, 1, 2)
        addLandBird(HSEntityTypes.GUINEA_FOWL.get(), listOf(BiomeTags.IS_SAVANNA), 3, 1, 3)
        addWaterBird(HSEntityTypes.PUFFIN.get(), listOf(HSBiomeTags.PUFFIN_SPAWN_BIOMES), 3, 2, 5)
        addWaterBird(HSEntityTypes.SEAGULL.get(), listOf(HSBiomeTags.SEAGULL_SPAWN_BIOMES), 3, 2, 5)
        addWaterBird(HSEntityTypes.ALBATROSS.get(), listOf(HSBiomeTags.ALBATROSS_SPAWN_BIOMES), 3, 2, 5)
        addWaterBird(HSEntityTypes.PELICAN.get(), listOf(HSBiomeTags.PELICAN_SPAWN_BIOMES), 1, 1, 2)
        addWaterBird(HSEntityTypes.DUCK.get(), listOf(HSBiomeTags.DUCK_SPAWN_BIOMES), 5, 1, 3)
        addWaterBird(HSEntityTypes.GOOSE.get(), listOf(HSBiomeTags.GOOSE_SPAWN_BIOMES), 3, 1, 3)
        addWaterBird(HSEntityTypes.SWAN.get(), listOf(HSBiomeTags.SWAN_SPAWN_BIOMES), 1, 1, 2)
    }

    private fun addLandBird(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("terrestrial_bird"),
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
            Services.PLATFORM.getHybridMobCategoryByName("aquatic_bird"),
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