package dev.hybridlabs.birds.effect

import dev.hybridlabs.birds.HybridBirds
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffect

object HybridBirdsStatusEffects {
    val ROOSTERS_CALLING = register("roosters_calling", RoostersCallingStatusEffect())

    private fun register(id: String, effect: MobEffect): MobEffect {
        return Registry.register(BuiltInRegistries.MOB_EFFECT, ResourceLocation(HybridBirds.MOD_ID, id), effect)
    }
}