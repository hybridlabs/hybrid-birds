package dev.hybridlabs.birds.sound

import dev.hybridlabs.birds.HybridBirdsCommon
import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent

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
    val ROOSTER_AMBIENT = register("rooster_ambient")
    val ROOSTER_HURT = register("rooster_hurt")
    val ROOSTER_DIE = register("rooster_die")

    val CHICK_AMBIENT = register("chick_ambient")
    val CHICK_HURT = register("chick_hurt")
    val CHICK_DIE = register("chick_die")

    val TURKEY_AMBIENT = register("turkey_ambient")
    val TURKEY_HURT = register("turkey_hurt")
    val TURKEY_DIE = register("turkey_die")

    val PEACOCK_AMBIENT = register("peacock_ambient")
    val PEACOCK_HURT = register("peacock_hurt")
    val PEACOCK_DIE = register("peacock_die")

    private fun register(id: String): RegistryObject<SoundEvent> {
        val identifier = ResourceLocation(Constants.MOD_ID, id)
        return HybridBirdsCommon.SOUND_EVENTS.register(id){SoundEvent.createVariableRangeEvent(identifier)}
    }
}