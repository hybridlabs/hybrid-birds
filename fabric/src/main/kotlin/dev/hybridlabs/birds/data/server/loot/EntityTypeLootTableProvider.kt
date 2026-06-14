package dev.hybridlabs.birds.data.server.loot

import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.item.HBItems
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
        export(exporter, HBEntityTypes.ROOSTER!!.get()) {
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

        export(exporter, HBEntityTypes.GUINEA_FOWL!!.get()) {
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

        export(exporter, HBEntityTypes.DUCK!!.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(HBItems.DUCK.get()))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                    .build()
            )
        }

        export(exporter, HBEntityTypes.GOOSE!!.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HBItems.GOOSE.get()))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                    .build()
            )
        }

        export(exporter, HBEntityTypes.TURKEY!!.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(HBItems.TURKEY.get()))
                    .build()
            )
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                    .build()
            )
        }

        export(exporter, HBEntityTypes.SWAN!!.get()) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                    .build()
            )
        }

        export(exporter, HBEntityTypes.PEACOCK!!.get()) {
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