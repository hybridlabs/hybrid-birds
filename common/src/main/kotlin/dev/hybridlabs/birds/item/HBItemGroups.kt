@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.birds.item

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.CommonClass
import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SpawnEggItem

object  HBItemGroups {

    val HYBRID_BIRDS = register(
        Constants.MOD_ID, CreativeModeTab.builder(CreativeModeTab.Row.TOP,0)
        .title(Component.translatable("itemGroup.${Constants.MOD_ID}.spawn_eggs"))
        .icon { ItemStack(HBItems.GOOSE.get()) }
        .displayItems { _, entries ->

            entries.accept(HBItems.COOKED_EGG.get())
            entries.accept(HBItems.SWAN_EGG.get())
            entries.accept(HBItems.PEACOCK_EGG.get())
            entries.accept(HBItems.GUINEA_FOWL_EGG.get())
            entries.accept(HBItems.DUCK_EGG.get())
            entries.accept(HBItems.DUCK.get())
            entries.accept(HBItems.COOKED_DUCK.get())
            entries.accept(HBItems.GOOSE_EGG.get())
            entries.accept(HBItems.GOOSE.get())
            entries.accept(HBItems.COOKED_GOOSE.get())
            entries.accept(HBItems.TURKEY_EGG.get())
            entries.accept(HBItems.TURKEY.get())
            entries.accept(HBItems.COOKED_TURKEY.get())
            entries.accept(HBItems.PUFFIN.get())
            entries.accept(HBItems.COOKED_PUFFIN.get())
            entries.accept(HBItems.TURDUCKEN.get())
            entries.accept(HBItems.COOKED_TURDUCKEN.get())

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

    private fun register(id: String, itemGroup: CreativeModeTab): RegistryObject<CreativeModeTab> {
        return CommonClass.CREATIVE_MODE_TABS.register(id) { itemGroup }
    }
}