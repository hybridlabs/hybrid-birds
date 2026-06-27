package dev.hybridlabs.birds.tag

import dev.hybridlabs.birds.Constants
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome

object HBBiomeTags {

    val PUFFIN_SPAWN_BIOMES = create("puffin_spawn_biomes")
    val SEAGULL_SPAWN_BIOMES = create("seagull_spawn_biomes")
    val ALBATROSS_SPAWN_BIOMES = create("albatross_spawn_biomes")
    val PELICAN_SPAWN_BIOMES = create("pelican_spawn_biomes")
    val DUCK_SPAWN_BIOMES = create("duck_spawn_biomes")
    val GOOSE_SPAWN_BIOMES = create("goose_spawn_biomes")
    val SWAN_SPAWN_BIOMES = create("swan_spawn_biomes")
    val FLAMINGO_SPAWN_BIOMES = create("flamingo_spawn_biomes")
    val ROOSTER_SPAWN_BIOMES = create("rooster_spawn_biomes")
    val TURKEY_SPAWN_BIOMES = create("turkey_spawn_biomes")
    val KIWI_SPAWN_BIOMES = create("kiwi_spawn_biomes")
    val HUMMINGBIRD_SPAWN_BIOMES = create("hummingbird_spawn_biomes")

    private fun create(id: String): TagKey<Biome> {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, id))
    }
}
