package dev.hybridlabs.skies.data.server.loot

import dev.hybridlabs.skies.item.HSItems
import dev.hybridlabs.skies.loot.HSLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class GenericLootTableProvider(output: FabricPackOutput,lookupProvider: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableSubProvider(output, lookupProvider,LootContextParamSets.ALL_PARAMS) {
    override fun generate(exporter: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {

        exporter.accept(
            ResourceKey.create(
                Registries.LOOT_TABLE,
            HSLootTables.TURKEY_FAT),
            LootTable.lootTable()
                .pool(LootPool.lootPool()
                        .add(lootTableItem(HSItems.TURKEY.get())
                            .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .build()
                )
                .pool(LootPool.lootPool()
                        .add(lootTableItem(Items.FEATHER)
                            .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3))))
                        .build()
                )
        )

        exporter.accept(
            ResourceKey.create(Registries.LOOT_TABLE,
            HSLootTables.TURKEY_STUFFED),
            LootTable.lootTable()
                .pool(LootPool.lootPool()
                    .add(lootTableItem(HSItems.TURKEY.get())
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3))))
                    .build()
                )
                .pool(LootPool.lootPool()
                    .add(lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4))))
                    .build()
                )
        )
    }
}
