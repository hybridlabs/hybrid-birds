package dev.hybridlabs.birds.config

import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.platform.Services
import dev.hybridlabs.birds.tag.HBBiomeTags
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
        addLandBird(HBEntityTypes.ROOSTER.get(), listOf(HBBiomeTags.ROOSTER_SPAWN_BIOMES), 5, 1, 2)
        addLandBird(HBEntityTypes.TURKEY.get(), listOf(HBBiomeTags.TURKEY_SPAWN_BIOMES), 3, 1, 3)
        addLandBird(HBEntityTypes.PEACOCK.get(), listOf(BiomeTags.IS_JUNGLE), 2, 1, 2)
        addLandBird(HBEntityTypes.GUINEA_FOWL.get(), listOf(BiomeTags.IS_SAVANNA), 3, 1, 3)
        //addLandBird(HBEntityTypes.HUMMINGBIRD.get(), listOf(HBBiomeTags.HUMMINGBIRD_SPAWN_BIOMES), 3, 1, 3)
        //addLandBird(HBEntityTypes.OSTRICH.get(), listOf(BiomeTags.IS_SAVANNA), 2, 1, 3)
        //addLandBird(HBEntityTypes.KIWI.get(), listOf(HBBiomeTags.KIWI_SPAWN_BIOMES), 2, 1, 1)
        //addLandBird(HBEntityTypes.JAY.get(), listOf(BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA), 3, 1, 3)
        addWaterBird(HBEntityTypes.PUFFIN.get(), listOf(HBBiomeTags.PUFFIN_SPAWN_BIOMES), 3, 2, 5)
        addWaterBird(HBEntityTypes.SEAGULL.get(), listOf(HBBiomeTags.SEAGULL_SPAWN_BIOMES), 3, 2, 5)
        addWaterBird(HBEntityTypes.ALBATROSS.get(), listOf(HBBiomeTags.ALBATROSS_SPAWN_BIOMES), 3, 2, 5)
        addWaterBird(HBEntityTypes.PELICAN.get(), listOf(HBBiomeTags.PELICAN_SPAWN_BIOMES), 1, 1, 2)
        //addWaterBird(HBEntityTypes.FLAMINGO.get(), listOf(HBBiomeTags.FLAMINGO_SPAWN_BIOMES), 2, 1, 3)
        addWaterBird(HBEntityTypes.DUCK.get(), listOf(HBBiomeTags.DUCK_SPAWN_BIOMES), 5, 1, 3)
        addWaterBird(HBEntityTypes.GOOSE.get(), listOf(HBBiomeTags.GOOSE_SPAWN_BIOMES), 3, 1, 3)
        addWaterBird(HBEntityTypes.SWAN.get(), listOf(HBBiomeTags.SWAN_SPAWN_BIOMES), 1, 1, 2)
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