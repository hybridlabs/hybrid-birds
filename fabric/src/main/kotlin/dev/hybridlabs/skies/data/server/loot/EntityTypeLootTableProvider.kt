package dev.hybridlabs.skies.data.server.loot

import dev.hybridlabs.skies.entity.HSEntityTypes
import dev.hybridlabs.skies.item.HSItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.advancements.critereon.EntityFlagsPredicate
import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class EntityTypeLootTableProvider(exporter: FabricDataOutput, val lookupProvider: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableProvider(exporter, lookupProvider, LootContextParamSets.ENTITY) {
    override fun generate(exporter: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {
        val lookup = lookupProvider.join()

        export(exporter, HSEntityTypes.PUFFIN.get().defaultLootTable) {
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

        export(exporter, HSEntityTypes.SEAGULL.get().defaultLootTable) {
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

        export(exporter, HSEntityTypes.ALBATROSS.get().defaultLootTable) {
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

        export(exporter, HSEntityTypes.PELICAN.get().defaultLootTable) {
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

        export(exporter, HSEntityTypes.ROOSTER.get().defaultLootTable) {
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

        export(exporter, HSEntityTypes.GUINEA_FOWL.get().defaultLootTable) {
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

        export(exporter, HSEntityTypes.DUCK.get().defaultLootTable) {
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

        export(exporter, HSEntityTypes.GOOSE.get().defaultLootTable) {
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

        export(exporter, HSEntityTypes.TURKEY.get().defaultLootTable) {
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

        export(exporter, HSEntityTypes.SWAN.get().defaultLootTable) {
            pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.FEATHER)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                    .build()
            )
        }

        export(exporter, HSEntityTypes.PEACOCK.get().defaultLootTable) {
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
        exporter: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>,
        entityTable: ResourceKey<LootTable>,
        builder: LootTable.Builder.() -> Unit
    ) {
        exporter.accept(entityTable, LootTable.lootTable().apply(builder))
    }

    companion object {
        private val NEEDS_ENTITY_ON_FIRE: EntityPredicate.Builder =
            EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
    }
}