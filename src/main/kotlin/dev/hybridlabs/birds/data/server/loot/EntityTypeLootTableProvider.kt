package dev.hybridlabs.birds.data.server.loot

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.item.HybridBirdsItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.entity.EntityType
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier
import java.util.function.BiConsumer

class EntityTypeLootTableProvider(output: FabricDataOutput) :
    SimpleFabricLootTableProvider(output, LootContextTypes.ENTITY) {
    override fun accept(exporter: BiConsumer<Identifier, LootTable.Builder>) {
        export(exporter, HybridBirdsEntityTypes.ROOSTER) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.CHICKEN)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.FEATHER)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridBirdsEntityTypes.GUINEA_FOWL) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.CHICKEN)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.FEATHER)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridBirdsEntityTypes.DUCK) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridBirdsItems.DUCK)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.FEATHER)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridBirdsEntityTypes.GOOSE) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridBirdsItems.GOOSE)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.FEATHER)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridBirdsEntityTypes.TURKEY) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridBirdsItems.TURKEY)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.FEATHER)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridBirdsEntityTypes.SWAN) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.FEATHER)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridBirdsEntityTypes.PEACOCK) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.FEATHER)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F)))
                    )
            )
        }
    }

    /**
     * Exports a loot table for [entityType] to [exporter] using its loot table id.
     */
    private fun export(
        exporter: BiConsumer<Identifier, LootTable.Builder>,
        entityType: EntityType<*>,
        builder: LootTable.Builder.() -> Unit
    ) {
        exporter.accept(entityType.lootTableId, LootTable.builder().apply(builder))
    }
}