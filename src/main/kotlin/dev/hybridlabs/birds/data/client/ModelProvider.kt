package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.data.HybridBirdsDataGenerator.filterHybridBirds
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Blocks
import net.minecraft.block.FluidBlock
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.ModelIds
import net.minecraft.data.client.TextureMap
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
        generator.run {

            // spawn eggs
            Registries.ITEM
                .filter(filterHybridBirds(Registries.ITEM))
                .forEach { item ->
                    if (item is SpawnEggItem) {
                        registerParentedItemModel(item, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"))
                    }
                }
        }
    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator?) {
        TODO("Not yet implemented")
    }

}