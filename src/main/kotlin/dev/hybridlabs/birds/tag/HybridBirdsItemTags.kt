@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.birds.tag

import dev.hybridlabs.birds.HybridBirds
import net.fabricmc.fabric.impl.tag.convention.TagRegistration
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object HybridBirdsItemTags {
    val TURDUCKEN_INGREDIENTS = create("turducken_ingredients")
    val EGGS = create("eggs")

    private fun create(id: String): TagKey<Item> {
        return TagKey.of(RegistryKeys.ITEM, Identifier(HybridBirds.MOD_ID, id))
    }

    private fun createConventional(id: String): TagKey<Item> {
        return TagRegistration.ITEM_TAG_REGISTRATION.registerCommon(id)
    }
}
