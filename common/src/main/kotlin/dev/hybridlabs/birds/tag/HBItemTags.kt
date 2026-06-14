@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.birds.tag

import dev.hybridlabs.birds.Constants
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item


object HBItemTags {
    val TURDUCKEN_INGREDIENTS = create("turducken_ingredients")
    val EGGS = create("eggs")

    private fun create(id: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, ResourceLocation(Constants.MOD_ID, id))
    }
}
