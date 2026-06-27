package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.data.HBDataGenerator.filterHybridBirds
import dev.hybridlabs.birds.item.HBItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.world.item.SpawnEggItem

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockModelGenerators) {
        // spawn eggs
        BuiltInRegistries.ITEM
            .filter(filterHybridBirds(BuiltInRegistries.ITEM))
            .forEach { item ->
                if (item is SpawnEggItem) {
                    generator.delegateItemModel(item, ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"))
                }
            }
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        generator.generateFlatItem(HBItems.DUCK_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.GOOSE_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.SWAN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.TURKEY_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.PEACOCK_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.GUINEA_FOWL_EGG.get(), ModelTemplates.FLAT_ITEM)

        generator.generateFlatItem(HBItems.COOKED_EGG.get(), ModelTemplates.FLAT_ITEM)

        generator.generateFlatItem(HBItems.PUFFIN.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.DUCK.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.GOOSE.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.TURKEY.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.TURDUCKEN.get(), ModelTemplates.FLAT_ITEM)

        generator.generateFlatItem(HBItems.COOKED_PUFFIN.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.COOKED_DUCK.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.COOKED_GOOSE.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.COOKED_TURKEY.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.COOKED_TURDUCKEN.get(), ModelTemplates.FLAT_ITEM)
    }
}
