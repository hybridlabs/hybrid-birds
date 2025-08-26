package dev.hybridlabs.birds.item

import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.EggItem

class CustomEggItem<T : Entity?>(settings: Properties, val type: RegistryObject<EntityType<T>>?) : EggItem(settings)