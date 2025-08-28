package dev.hybridlabs.birds.data.server.loot

import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.loot.HybridBirdsLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.function.BiConsumer

class GenericLootTableProvider(output: FabricDataOutput) :
    SimpleFabricLootTableProvider(output, LootContextParamSets.ALL_PARAMS) {
    override fun generate(exporter: BiConsumer<ResourceLocation, LootTable.Builder>) {
        exporter.accept(
            HybridBirdsLootTables.TURKEY_FAT,
            LootTable.lootTable()
                .pool(LootPool.lootPool()
                        .add(lootTableItem(HybridBirdsItems.TURKEY.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f))))
                        .build()
                )
                .pool(LootPool.lootPool()
                        .add(lootTableItem(Items.FEATHER)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 3.0f))))
                        .build()
                )
        )
        exporter.accept(
            HybridBirdsLootTables.TURKEY_STUFFED,
            LootTable.lootTable()
                .pool(LootPool.lootPool()
                    .add(lootTableItem(HybridBirdsItems.TURKEY.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 3.0f))))
                    .build()
                )
                .pool(LootPool.lootPool()
                    .add(lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 4.0f))))
                    .build()
                )
        )
    }
}
