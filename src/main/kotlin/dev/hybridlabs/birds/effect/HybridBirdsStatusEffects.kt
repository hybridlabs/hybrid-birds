package dev.hybridlabs.birds.effect

import dev.hybridlabs.birds.HybridBirds
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridBirdsStatusEffects {
    val ROOSTERS_CALLING = register("roosters_calling", RoostersCallingStatusEffect())

    private fun register(id: String, effect: StatusEffect): StatusEffect {
        return Registry.register(Registries.STATUS_EFFECT, Identifier(HybridBirds.MOD_ID, id), effect)
    }
}