package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.item.HBItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockModelGenerators) {
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        generator.generateFlatItem(HBItems.ROOSTER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.DUCK_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.GOOSE_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.SWAN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.TURKEY_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.PEACOCK_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.GUINEA_FOWL_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.PUFFIN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.SEAGULL_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.PELICAN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HBItems.ALBATROSS_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)

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
