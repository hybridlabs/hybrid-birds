@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.birds.item

import dev.hybridlabs.birds.Constants
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
        .title(Component.translatable("itemGroup.${Constants.MOD_ID}.spawn_eggs"))
        .icon { ItemStack(HybridBirdsItems.GOOSE.get()) }
        .displayItems { _, entries ->

            entries.accept(HybridBirdsItems.COOKED_EGG.get())
            entries.accept(HybridBirdsItems.SWAN_EGG.get())
            entries.accept(HybridBirdsItems.PEACOCK_EGG.get())
            entries.accept(HybridBirdsItems.GUINEA_FOWL_EGG.get())
            entries.accept(HybridBirdsItems.DUCK_EGG.get())
            entries.accept(HybridBirdsItems.DUCK.get())
            entries.accept(HybridBirdsItems.COOKED_DUCK.get())
            entries.accept(HybridBirdsItems.GOOSE_EGG.get())
            entries.accept(HybridBirdsItems.GOOSE.get())
            entries.accept(HybridBirdsItems.COOKED_GOOSE.get())
            entries.accept(HybridBirdsItems.TURKEY_EGG.get())
            entries.accept(HybridBirdsItems.TURKEY.get())
            entries.accept(HybridBirdsItems.COOKED_TURKEY.get())
            entries.accept(HybridBirdsItems.TURDUCKEN.get())
            entries.accept(HybridBirdsItems.COOKED_TURDUCKEN.get())

            BuiltInRegistries.ITEM.forEach { item ->
                val id = BuiltInRegistries.ITEM.getKey(item)
                if (id.namespace != Constants.MOD_ID) {
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
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation(Constants.MOD_ID, id), itemGroup)
    }
}