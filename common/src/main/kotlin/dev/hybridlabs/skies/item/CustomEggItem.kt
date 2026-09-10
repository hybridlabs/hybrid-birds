package dev.hybridlabs.skies.item

import dev.hybridlabs.skies.platform.registration.RegistryObject
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.EggItem

class CustomEggItem<T : Entity?>(settings: Properties, val type: RegistryObject<EntityType<T>>?) : EggItem(settings)