@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.birds.tag

import dev.hybridlabs.birds.HybridBirds
import net.fabricmc.fabric.impl.tag.convention.TagRegistration
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object HybridBirdsItemTags {
    val TURDUCKEN_INGREDIENTS = create("turducken_ingredients")
    val EGGS = create("eggs")

    private fun create(id: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, ResourceLocation(HybridBirds.MOD_ID, id))
    }

    private fun createConventional(id: String): TagKey<Item> {
        return TagRegistration.ITEM_TAG_REGISTRATION.registerCommon(id)
    }
}
