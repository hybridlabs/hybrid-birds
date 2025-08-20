package dev.hybridlabs.birds.effect

import dev.hybridlabs.birds.HybridBirds
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier

object HybridBirdsStatusEffects {
    val ROOSTERS_CALLING = register("roosters_calling", RoostersCallingStatusEffect())

    private fun register(id: String, effect: StatusEffect): RegistryEntry<StatusEffect> {
        return Registry.registerReference(
            Registries.STATUS_EFFECT,
                Identifier.of(HybridBirds.MOD_ID, id),
            effect
        )
    }
}