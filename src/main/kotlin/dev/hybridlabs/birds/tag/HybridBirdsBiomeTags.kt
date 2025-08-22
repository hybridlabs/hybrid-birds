package dev.hybridlabs.birds.tag

import dev.hybridlabs.birds.HybridBirds
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome

object HybridBirdsBiomeTags {

    val DUCK_SPAWN_BIOMES = create("duck_spawn_biomes")
    val GOOSE_SPAWN_BIOMES = create("goose_spawn_biomes")
    val SWAN_SPAWN_BIOMES = create("swan_spawn_biomes")
    val ROOSTER_SPAWN_BIOMES = create("rooster_spawn_biomes")
    val TURKEY_SPAWN_BIOMES = create("turkey_spawn_biomes")

    private fun create(id: String): TagKey<Biome> {
        return TagKey.create(Registries.BIOME, ResourceLocation(HybridBirds.MOD_ID, id))
    }
}
