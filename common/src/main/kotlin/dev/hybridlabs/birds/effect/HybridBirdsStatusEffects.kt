package dev.hybridlabs.birds.effect

import dev.hybridlabs.birds.CommonClass
import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffect

object HybridBirdsStatusEffects {
    @JvmStatic
    fun load(){}

    val ROOSTERS_CALLING = register("roosters_calling", RoostersCallingStatusEffect())

    private fun register(id: String, effect: MobEffect): RegistryObject<MobEffect> {
        return CommonClass.MOB_EFFECTS.register(id){effect}
    }
}