package dev.hybridlabs.skies.util

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient

/**
 * Builds an ingredient matching an item tag.
 *
 * 1.21.11 removed `Ingredient.of(TagKey)`, so the tag has to be resolved through the item registry.
 * The lookup only works after tags are bound, hence callers must resolve it lazily rather than
 * storing the result in an eagerly initialised companion field.
 */
fun ingredientOf(tag: TagKey<Item>): Ingredient =
    Ingredient.of(BuiltInRegistries.ITEM.getOrThrow(tag))
