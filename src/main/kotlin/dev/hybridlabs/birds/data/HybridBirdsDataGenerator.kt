package dev.hybridlabs.birds.data

import dev.hybridlabs.birds.HybridBirds
import dev.hybridlabs.birds.data.client.LanguageProvider
import dev.hybridlabs.birds.data.client.ModelProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.Registry

object HybridBirdsDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
		val pack = generator.createPack()
		pack.addProvider(::LanguageProvider)
		pack.addProvider(::ModelProvider)
	}

	fun <T> filterHybridBirds(registry: Registry<T>): (T) -> Boolean {
		return { o ->
			val id = registry.getId(o)
			id?.namespace == HybridBirds.MOD_ID
		}
	}
}
