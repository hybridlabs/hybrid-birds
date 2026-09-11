@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.skies.item

import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.CommonClass
import dev.hybridlabs.skies.platform.registration.RegistryObject
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SpawnEggItem

object  HSItemGroups {

    val HYBRID_BIRDS = register(
        Constants.MOD_ID, CreativeModeTab.builder(CreativeModeTab.Row.TOP,0)
        .title(Component.translatable("itemGroup.${Constants.MOD_ID}.spawn_eggs"))
        .icon { ItemStack(HSItems.GOOSE.get()) }
        .displayItems { _, entries ->

            entries.accept(HSItems.COOKED_EGG.get())
            entries.accept(HSItems.SWAN_EGG.get())
            entries.accept(HSItems.PEACOCK_EGG.get())
            entries.accept(HSItems.GUINEA_FOWL_EGG.get())
            entries.accept(HSItems.DUCK_EGG.get())
            entries.accept(HSItems.DUCK.get())
            entries.accept(HSItems.COOKED_DUCK.get())
            entries.accept(HSItems.GOOSE_EGG.get())
            entries.accept(HSItems.GOOSE.get())
            entries.accept(HSItems.COOKED_GOOSE.get())
            entries.accept(HSItems.TURKEY_EGG.get())
            entries.accept(HSItems.TURKEY.get())
            entries.accept(HSItems.COOKED_TURKEY.get())
            entries.accept(HSItems.TURDUCKEN.get())
            entries.accept(HSItems.COOKED_TURDUCKEN.get())
            entries.accept(HSItems.PUFFIN.get())
            entries.accept(HSItems.COOKED_PUFFIN.get())

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