package dev.hybridlabs.birds.item

import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.EggItem

class CustomEggItem(settings: Properties, val type: EntityType<*>) : EggItem(settings)