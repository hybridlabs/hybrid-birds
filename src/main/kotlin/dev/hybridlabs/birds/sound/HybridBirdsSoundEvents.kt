package dev.hybridlabs.birds.sound

import dev.hybridlabs.birds.HybridBirds
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

object HybridBirdsSoundEvents {
    private fun register(id: String): SoundEvent {
        val identifier = Identifier(HybridBirds.MOD_ID, id)
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier))
    }
}
