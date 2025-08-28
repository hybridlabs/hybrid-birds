package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.data.HybridBirdsDataGenerator.filterHybridBirds
import dev.hybridlabs.birds.item.HybridBirdsItems
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
        generator.generateFlatItem(HybridBirdsItems.DUCK_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.GOOSE_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.SWAN_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.TURKEY_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.PEACOCK_EGG.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.GUINEA_FOWL_EGG.get(), ModelTemplates.FLAT_ITEM)

        generator.generateFlatItem(HybridBirdsItems.COOKED_EGG.get(), ModelTemplates.FLAT_ITEM)

        generator.generateFlatItem(HybridBirdsItems.DUCK.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.GOOSE.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.TURKEY.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.TURDUCKEN.get(), ModelTemplates.FLAT_ITEM)

        generator.generateFlatItem(HybridBirdsItems.COOKED_DUCK.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.COOKED_GOOSE.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.COOKED_TURKEY.get(), ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(HybridBirdsItems.COOKED_TURDUCKEN.get(), ModelTemplates.FLAT_ITEM)
    }
}
