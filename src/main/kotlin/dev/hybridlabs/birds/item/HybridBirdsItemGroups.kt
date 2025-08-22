@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.birds.item

import dev.hybridlabs.birds.HybridBirds
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SpawnEggItem

object  HybridBirdsItemGroups {
    val HYBRID_BIRDS = register("hybrid_birds", FabricItemGroup.builder()
        .title(Component.translatable("itemGroup.${HybridBirds.MOD_ID}.spawn_eggs"))
        .icon { ItemStack(HybridBirdsItems.GOOSE) }
        .displayItems { _, entries ->

            entries.accept(HybridBirdsItems.COOKED_EGG)
            entries.accept(HybridBirdsItems.SWAN_EGG)
            entries.accept(HybridBirdsItems.PEACOCK_EGG)
            entries.accept(HybridBirdsItems.GUINEA_FOWL_EGG)
            entries.accept(HybridBirdsItems.DUCK_EGG)
            entries.accept(HybridBirdsItems.DUCK)
            entries.accept(HybridBirdsItems.COOKED_DUCK)
            entries.accept(HybridBirdsItems.GOOSE_EGG)
            entries.accept(HybridBirdsItems.GOOSE)
            entries.accept(HybridBirdsItems.COOKED_GOOSE)
            entries.accept(HybridBirdsItems.TURKEY_EGG)
            entries.accept(HybridBirdsItems.TURKEY)
            entries.accept(HybridBirdsItems.COOKED_TURKEY)
            entries.accept(HybridBirdsItems.TURDUCKEN)
            entries.accept(HybridBirdsItems.COOKED_TURDUCKEN)

            BuiltInRegistries.ITEM.forEach { item ->
                val id = BuiltInRegistries.ITEM.getKey(item)
                if (id.namespace != HybridBirds.MOD_ID) {
                    return@forEach
                }
                if (item is SpawnEggItem) {
                    entries.accept(item)
                }
            }
        }
        .build()
    )

    private fun register(id: String, itemGroup: CreativeModeTab): _root_ide_package_.net.minecraft.world.item.CreativeModeTab {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation(HybridBirds.MOD_ID, id), itemGroup)
    }
}