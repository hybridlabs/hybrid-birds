package dev.hybridlabs.birds.sound

import dev.hybridlabs.birds.HybridBirds
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

object HybridBirdsSoundEvents {

    val DUCK_AMBIENT = register("duck_ambient")
    val DUCK_HURT = register("duck_hurt")
    val DUCK_DIE = register("duck_die")

    val GOOSE_AMBIENT = register("goose_ambient")
    val GOOSE_HURT = register("goose_hurt")
    val GOOSE_DIE = register("goose_die")

    val SWAN_AMBIENT = register("swan_ambient")
    val SWAN_HURT = register("swan_hurt")
    val SWAN_DIE = register("swan_die")

    val ROOSTER_CALL = register("rooster_call")

    val TURKEY_AMBIENT = register("turkey_ambient")
    val TURKEY_HURT = register("turkey_hurt")
    val TURKEY_DIE = register("turkey_die")

    val PEACOCK_AMBIENT = register("peacock_ambient")
    val PEACOCK_HURT = register("peacock_hurt")
    val PEACOCK_DIE = register("peacock_die")

    private fun register(id: String): SoundEvent {
        val identifier = Identifier(HybridBirds.MOD_ID, id)
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier))
    }
}
