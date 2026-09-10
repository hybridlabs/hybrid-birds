package dev.hybridlabs.skies.data.server.loot

import dev.hybridlabs.skies.entity.HSEntityTypes
import dev.hybridlabs.skies.item.HSItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.function.BiConsumer

class EntityTypeLootTableProvider(output: FabricDataOutput) :
    SimpleFabricLootTableProvider(output, LootContextParamSets.ENTITY) {
    override fun generate(exporter: BiConsumer<ResourceLocation, LootTable.Builder>) {
        export(exporter, HSEntityTypes.PUFFIN.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(HSItems.PUFFIN.get()))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.SEAGULL.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.COD)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.ALBATROSS.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.COD)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.PELICAN.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.COD)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.ROOSTER.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.CHICKEN))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.GUINEA_FOWL.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.CHICKEN))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.DUCK.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(HSItems.DUCK.get()))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.GOOSE.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HSItems.GOOSE.get()))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.TURKEY.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(HSItems.TURKEY.get()))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.SWAN.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.PEACOCK.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                    .build()
            )
        }
    }

    /**
     * Exports a loot table for [entityType] to [exporter] using its loot table id.
     */
    private fun export(
        exporter: BiConsumer<ResourceLocation, LootTable.Builder>,
        entityType: EntityType<*>,
        builder: LootTable.Builder.() -> Unit
    ) {
        exporter.accept(entityType.defaultLootTable, LootTable.lootTable().apply(builder))
    }
}