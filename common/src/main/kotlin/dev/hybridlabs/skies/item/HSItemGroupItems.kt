package dev.hybridlabs.skies.item

import dev.hybridlabs.skies.platform.registration.RegistryObject
import net.minecraft.world.item.Item

/** Items shown in the Hybrid Birds creative tab, in display order. Spawn eggs are appended at runtime. */
object HSItemGroupItems {
    val DISPLAY_ITEMS: List<RegistryObject<out Item>> = listOf(
        HSItems.COOKED_EGG,
        HSItems.SWAN_EGG,
        HSItems.PEACOCK_EGG,
        HSItems.GUINEA_FOWL_EGG,
        HSItems.DUCK_EGG,
        HSItems.DUCK,
        HSItems.COOKED_DUCK,
        HSItems.GOOSE_EGG,
        HSItems.GOOSE,
        HSItems.COOKED_GOOSE,
        HSItems.TURKEY_EGG,
        HSItems.TURKEY,
        HSItems.COOKED_TURKEY,
        HSItems.TURDUCKEN,
        HSItems.COOKED_TURDUCKEN,
        HSItems.PUFFIN,
        HSItems.COOKED_PUFFIN,
    )
}
