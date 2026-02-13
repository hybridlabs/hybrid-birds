package dev.hybridlabs.birds.data.server.tag

import dev.hybridlabs.birds.tag.HybridBirdsBiomeTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import java.util.concurrent.CompletableFuture

class BiomeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<Biome>(output, Registries.BIOME, registriesFuture) {

    override fun addTags(arg: HolderLookup.Provider) {
        // spawn biomes

        getOrCreateTagBuilder(HybridBirdsBiomeTags.DUCK_SPAWN_BIOMES)
            .add(Biomes.RIVER)
            .add(Biomes.SWAMP)
            .addOptional(ResourceLocation("wythers", "jungle_river"))
            .addOptional(ResourceLocation("wythers", "tropical_forest_river"))
            .addOptional(ResourceLocation("terralith", "warm_river"))
            .addOptional(ResourceLocation("regions_unexplored", "tropical_river"))
            .addOptional(ResourceLocation("regions_unexplored", "muddy_river"))
            .addOptional(ResourceLocation("regions_unexplored", "cold_river"))
            .addOptional(ResourceLocation("riverredux", "sandy_river"))
            .addOptional(ResourceLocation("riverredux", "gravelly_river"))
            .addOptional(ResourceLocation("riverredux", "tropical_river"))
            .addOptional(ResourceLocation("riverredux", "carved_river"))
            .addOptional(ResourceLocation("climaterivers", "cold_river"))
            .addOptional(ResourceLocation("climaterivers", "lukewarm_river"))
            .addOptional(ResourceLocation("climaterivers", "warm_river"))
            .addOptional(ResourceLocation("still_life", "boreal_river"))
            .addOptional(ResourceLocation("still_life", "desert_river"))
            .addOptional(ResourceLocation("still_life", "mediterranean_river"))
            .addOptional(ResourceLocation("still_life", "savanna_river"))
            .addOptional(ResourceLocation("still_life", "steppe_river"))
            .addOptional(ResourceLocation("still_life", "temperate_river"))
            .addOptional(ResourceLocation("still_life", "tropical_rainforest_river"))
            .addOptional(ResourceLocation("still_life", "tundra_river"))
            .addOptional(ResourceLocation("still_life", "warm_temperate_river"))
            .addOptional(ResourceLocation("wythers", "waterlily_swamp"))
            .addOptional(ResourceLocation("terralith", "orchid_swamp"))
            .addOptional(ResourceLocation("biomesoplenty", "bayou"))
            .addOptional(ResourceLocation("biomeswevegone", "cypress_swamplands"))
            .addOptional(ResourceLocation("biomeswevegone", "cypress_wetlands"))
            .addOptional(ResourceLocation("biomeswevegone", "bayou"))
            .addOptional(ResourceLocation("terrestria", "cypress_swamp"))
            .addOptional(ResourceLocation("still_life", "mediterranean_swamp"))
            .addOptional(ResourceLocation("still_life", "temperate_swamp"))
            .addOptional(ResourceLocation("regions_unexplored", "marsh"))
            .addOptional(ResourceLocation("biomesoplenty", "marsh"))
            .addOptional(ResourceLocation("biomesoplenty", "wetland"))
            .addOptional(ResourceLocation("biomesoplenty", "floodplain"))
            .addOptional(ResourceLocation("still_life", "mediterranean_marsh"))
            .addOptional(ResourceLocation("still_life", "temperate_marsh"))

        getOrCreateTagBuilder(HybridBirdsBiomeTags.GOOSE_SPAWN_BIOMES)
            .add(Biomes.RIVER)
            .add(Biomes.TAIGA)
            .addOptional(ResourceLocation("regions_unexplored", "cold_river"))
            .addOptional(ResourceLocation("riverredux", "gravelly_river"))
            .addOptional(ResourceLocation("wythers", "waterlily_swamp"))
            .addOptional(ResourceLocation("terralith", "orchid_swamp"))
            .addOptional(ResourceLocation("biomesoplenty", "bayou"))
            .addOptional(ResourceLocation("biomeswevegone", "cypress_swamplands"))
            .addOptional(ResourceLocation("biomeswevegone", "cypress_wetlands"))
            .addOptional(ResourceLocation("biomeswevegone", "bayou"))
            .addOptional(ResourceLocation("terrestria", "cypress_swamp"))
            .addOptional(ResourceLocation("still_life", "mediterranean_swamp"))
            .addOptional(ResourceLocation("still_life", "temperate_swamp"))
            .addOptional(ResourceLocation("regions_unexplored", "marsh"))
            .addOptional(ResourceLocation("biomesoplenty", "marsh"))
            .addOptional(ResourceLocation("biomesoplenty", "wetland"))
            .addOptional(ResourceLocation("biomesoplenty", "floodplain"))
            .addOptional(ResourceLocation("still_life", "mediterranean_marsh"))
            .addOptional(ResourceLocation("still_life", "temperate_marsh"))

        getOrCreateTagBuilder(HybridBirdsBiomeTags.SWAN_SPAWN_BIOMES)
            .add(Biomes.RIVER)
            .add(Biomes.CHERRY_GROVE)
            .addOptional(ResourceLocation("regions_unexplored", "cold_river"))
            .addOptional(ResourceLocation("riverredux", "gravelly_river"))
            .addOptional(ResourceLocation("regions_unexplored", "mauve_hills"))
            .addOptional(ResourceLocation("regions_unexplored", "magnolia_woodland"))

        getOrCreateTagBuilder(HybridBirdsBiomeTags.ROOSTER_SPAWN_BIOMES)
            .forceAddTag(BiomeTags.HAS_VILLAGE_PLAINS)
            .add(Biomes.PLAINS)
            .add(Biomes.SUNFLOWER_PLAINS)
            .add(Biomes.FOREST)
            .add(Biomes.FLOWER_FOREST)
            .add(Biomes.BIRCH_FOREST)

        getOrCreateTagBuilder(HybridBirdsBiomeTags.TURKEY_SPAWN_BIOMES)
            .forceAddTag(BiomeTags.IS_FOREST)
            .forceAddTag(BiomeTags.IS_TAIGA)
            .add(Biomes.FOREST)
            .add(Biomes.DARK_FOREST)
            .add(Biomes.TAIGA)
            .add(Biomes.OLD_GROWTH_PINE_TAIGA)
            .add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)

        getOrCreateTagBuilder(HybridBirdsBiomeTags.KIWI_SPAWN_BIOMES)
            .add(Biomes.OLD_GROWTH_PINE_TAIGA)
            .add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)

        getOrCreateTagBuilder(HybridBirdsBiomeTags.HUMMINGBIRD_SPAWN_BIOMES)
            .add(Biomes.FLOWER_FOREST)
            .add(Biomes.SUNFLOWER_PLAINS)
    }
}
