package dev.hybridlabs.birds.data.server.loot

import dev.hybridlabs.birds.loot.HybridBirdsLootTables
import dev.hybridlabs.birds.item.HybridBirdsItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier
import java.util.function.BiConsumer

class GenericLootTableProvider(output: FabricDataOutput) :
    SimpleFabricLootTableProvider(output, LootContextTypes.GENERIC) {
    override fun accept(exporter: BiConsumer<Identifier, LootTable.Builder>) {
        exporter.accept(
            HybridBirdsLootTables.TURKEY_FAT,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridBirdsItems.TURKEY)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                )
        )
        exporter.accept(
            HybridBirdsLootTables.TURKEY_STUFFED,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridBirdsItems.TURKEY)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 3.0f)))
                        )
                )
        )
    }
}
