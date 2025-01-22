package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.data.HybridBirdsDataGenerator.filterHybridBirds
import dev.hybridlabs.birds.item.HybridBirdsItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.ModelIds
import net.minecraft.data.client.Models
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
        // spawn eggs
        Registries.ITEM
            .filter(filterHybridBirds(Registries.ITEM))
            .forEach { item ->
                if (item is SpawnEggItem) {
                    generator.registerParentedItemModel(item, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"))
                }
            }
    }

    override fun generateItemModels(generator: ItemModelGenerator) {
        generator.register(HybridBirdsItems.TURKEY_EGG, Models.GENERATED)
    }
}
