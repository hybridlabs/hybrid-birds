package dev.hybridlabs.birds.data.server.tag

import dev.hybridlabs.birds.tag.HybridBirdsBiomeTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeKeys
import java.util.concurrent.CompletableFuture

class BiomeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricTagProvider<Biome>(output, RegistryKeys.BIOME, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup?) {
        // spawn biomes

        getOrCreateTagBuilder(HybridBirdsBiomeTags.DUCK_SPAWN_BIOMES)
            .add(BiomeKeys.RIVER)
            .add(BiomeKeys.SWAMP)
            .addOptional(Identifier("wythers", "jungle_river"))
            .addOptional(Identifier("wythers", "tropical_forest_river"))
            .addOptional(Identifier("terralith", "warm_river"))
            .addOptional(Identifier("regions_unexplored", "tropical_river"))
            .addOptional(Identifier("regions_unexplored", "muddy_river"))
            .addOptional(Identifier("regions_unexplored", "cold_river"))
            .addOptional(Identifier("riverredux", "sandy_river"))
            .addOptional(Identifier("riverredux", "gravelly_river"))
            .addOptional(Identifier("riverredux", "tropical_river"))
            .addOptional(Identifier("riverredux", "carved_river"))
            .addOptional(Identifier("wythers", "waterlily_swamp"))
            .addOptional(Identifier("terralith", "orchid_swamp"))
            .addOptional(Identifier("biomesoplenty", "bayou"))
            .addOptional(Identifier("biomeswevegone", "cypress_swamplands"))
            .addOptional(Identifier("biomeswevegone", "cypress_wetlands"))
            .addOptional(Identifier("biomeswevegone", "bayou"))
            .addOptional(Identifier("terrestria", "cypress_swamp"))
            .addOptional(Identifier("regions_unexplored", "marsh"))
            .addOptional(Identifier("biomesoplenty", "marsh"))
            .addOptional(Identifier("biomesoplenty", "wetland"))
            .addOptional(Identifier("biomesoplenty", "floodplain"))

        getOrCreateTagBuilder(HybridBirdsBiomeTags.GOOSE_SPAWN_BIOMES)
            .add(BiomeKeys.RIVER)
            .add(BiomeKeys.TAIGA)
            .addOptional(Identifier("regions_unexplored", "cold_river"))
            .addOptional(Identifier("riverredux", "gravelly_river"))
            .addOptional(Identifier("wythers", "waterlily_swamp"))
            .addOptional(Identifier("terralith", "orchid_swamp"))
            .addOptional(Identifier("biomesoplenty", "bayou"))
            .addOptional(Identifier("biomeswevegone", "cypress_swamplands"))
            .addOptional(Identifier("biomeswevegone", "cypress_wetlands"))
            .addOptional(Identifier("biomeswevegone", "bayou"))
            .addOptional(Identifier("terrestria", "cypress_swamp"))
            .addOptional(Identifier("regions_unexplored", "marsh"))
            .addOptional(Identifier("biomesoplenty", "marsh"))
            .addOptional(Identifier("biomesoplenty", "wetland"))
            .addOptional(Identifier("biomesoplenty", "floodplain"))

        getOrCreateTagBuilder(HybridBirdsBiomeTags.SWAN_SPAWN_BIOMES)
            .add(BiomeKeys.RIVER)
            .add(BiomeKeys.CHERRY_GROVE)
            .addOptional(Identifier("regions_unexplored", "cold_river"))
            .addOptional(Identifier("riverredux", "gravelly_river"))
            .addOptional(Identifier("regions_unexplored", "mauve_hills"))
            .addOptional(Identifier("regions_unexplored", "magnolia_woodland"))

        getOrCreateTagBuilder(HybridBirdsBiomeTags.ROOSTER_SPAWN_BIOMES)
            .add(BiomeKeys.PLAINS)
            .add(BiomeKeys.SUNFLOWER_PLAINS)
            .add(BiomeKeys.FOREST)
            .add(BiomeKeys.FLOWER_FOREST)
            .add(BiomeKeys.BIRCH_FOREST)

        getOrCreateTagBuilder(HybridBirdsBiomeTags.TURKEY_SPAWN_BIOMES)
            .add(BiomeKeys.FOREST)
            .add(BiomeKeys.DARK_FOREST)
            .add(BiomeKeys.TAIGA)
            .add(BiomeKeys.OLD_GROWTH_PINE_TAIGA)
            .add(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA)
    }
}
