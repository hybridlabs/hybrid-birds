package dev.hybridlabs.skies.item

import dev.hybridlabs.skies.CommonClass
import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.platform.registration.RegistryObject
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SpawnEggItem

/**
 * The creative tab is built in the loader module. In 26.x [CreativeModeTab.Output] is protected in
 * vanilla, and only the loader-patched classpath (Fabric API/NeoForge) makes it usable.
 */
object HSItemGroups {

    val HYBRID_BIRDS: RegistryObject<CreativeModeTab> =
        CommonClass.CREATIVE_MODE_TABS.register(Constants.MOD_ID) {
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable("itemGroup.${Constants.MOD_ID}.spawn_eggs"))
                .icon { ItemStack(HSItems.GOOSE.get()) }
                .displayItems { _, output ->
                    HSItemGroupItems.DISPLAY_ITEMS.forEach { output.accept(it.get()) }

                    BuiltInRegistries.ITEM.forEach { item ->
                        if (BuiltInRegistries.ITEM.getKey(item).namespace == Constants.MOD_ID && item is SpawnEggItem) {
                            output.accept(item)
                        }
                    }
                }
                .build()
        }
}
