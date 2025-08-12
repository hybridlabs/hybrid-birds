@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.birds.item

import dev.hybridlabs.birds.HybridBirds
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object  HybridBirdsItemGroups {
    val HYBRID_BIRDS = register("hybrid_birds", FabricItemGroup.builder()
        .displayName(Text.translatable("itemGroup.${HybridBirds.MOD_ID}.spawn_eggs"))
        .icon { ItemStack(Items.FEATHER) }
        .entries { _, entries ->

            entries.add(HybridBirdsItems.DUCK_EGG)
            entries.add(HybridBirdsItems.GOOSE_EGG)
            entries.add(HybridBirdsItems.SWAN_EGG)
            entries.add(HybridBirdsItems.TURKEY_EGG)
            entries.add(HybridBirdsItems.PEACOCK_EGG)
            entries.add(HybridBirdsItems.GUINEA_FOWL_EGG)
            entries.add(HybridBirdsItems.RAW_DUCK)
            entries.add(HybridBirdsItems.RAW_GOOSE)
            entries.add(HybridBirdsItems.RAW_TURKEY)
            entries.add(HybridBirdsItems.RAW_TURDUCKEN)
            entries.add(HybridBirdsItems.COOKED_DUCK)
            entries.add(HybridBirdsItems.COOKED_GOOSE)
            entries.add(HybridBirdsItems.COOKED_TURKEY)
            entries.add(HybridBirdsItems.COOKED_TURDUCKEN)

            Registries.ITEM.forEach { item ->
                val id = Registries.ITEM.getId(item)
                if (id.namespace != HybridBirds.MOD_ID) {
                    return@forEach
                }
                if (item is SpawnEggItem) {
                    entries.add(item)
                }
            }
        }
        .build()
    )

    private fun register(id: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, Identifier(HybridBirds.MOD_ID, id), itemGroup)
    }
}