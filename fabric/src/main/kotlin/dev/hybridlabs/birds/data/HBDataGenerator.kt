package dev.hybridlabs.birds.data

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.data.client.LanguageProvider
import dev.hybridlabs.birds.data.client.ModelProvider
import dev.hybridlabs.birds.data.client.SoundProvider
import dev.hybridlabs.birds.data.server.RecipeProvider
import dev.hybridlabs.birds.data.server.loot.EntityTypeLootTableProvider
import dev.hybridlabs.birds.data.server.loot.GenericLootTableProvider
import dev.hybridlabs.birds.data.server.tag.BiomeTagProvider
import dev.hybridlabs.birds.data.server.tag.ItemTagProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.core.Registry

object HBDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
		val pack = generator.createPack()
		pack.addProvider(::LanguageProvider)
		pack.addProvider(::ModelProvider)
        pack.addProvider(::SoundProvider)
		pack.addProvider(::EntityTypeLootTableProvider)
		pack.addProvider(::GenericLootTableProvider)
		pack.addProvider(::BiomeTagProvider)
		pack.addProvider(::ItemTagProvider)
		pack.addProvider(::RecipeProvider)
	}

	fun <T> filterHybridBirds(registry: Registry<T>): (T) -> Boolean {
		return { o ->
			val id = registry.getKey(o)
			id?.namespace == Constants.MOD_ID
		}
	}
}
