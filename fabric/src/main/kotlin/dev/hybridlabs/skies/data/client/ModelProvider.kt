package dev.hybridlabs.skies.data.client

import dev.hybridlabs.skies.item.HSItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockModelGenerators) {
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        generator.generateFlatItem(HSItems.ROOSTER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.DUCK_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.GOOSE_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.SWAN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.TURKEY_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.PEACOCK_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.GUINEA_FOWL_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.PUFFIN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.SEAGULL_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.PELICAN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.ALBATROSS_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM)

        generator.generateFlatItem(HSItems.DUCK_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.GOOSE_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.SWAN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.TURKEY_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.PEACOCK_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.GUINEA_FOWL_EGG.get(), ModelTemplates.FLAT_ITEM)

        generator.generateFlatItem(HSItems.COOKED_EGG.get(), ModelTemplates.FLAT_ITEM)

        generator.generateFlatItem(HSItems.PUFFIN.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.DUCK.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.GOOSE.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.TURKEY.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.TURDUCKEN.get(), ModelTemplates.FLAT_ITEM)

        generator.generateFlatItem(HSItems.COOKED_PUFFIN.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.COOKED_DUCK.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.COOKED_GOOSE.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.COOKED_TURKEY.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HSItems.COOKED_TURDUCKEN.get(), ModelTemplates.FLAT_ITEM)
    }
}
