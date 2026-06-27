package dev.hybridlabs.birds.sound

import dev.hybridlabs.birds.CommonClass
import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent

object HBSoundEvents {

    val PUFFIN_AMBIENT = register("entity.puffin.ambient")
    val PUFFIN_HURT = register("entity.puffin.hurt")
    val PUFFIN_DIE = register("entity.puffin.die")
    
    val SEAGULL_AMBIENT = register("entity.seagull.ambient")
    val SEAGULL_HURT = register("entity.seagull.hurt")
    val SEAGULL_DIE = register("entity.seagull.die")

    val PELICAN_AMBIENT = register("entity.pelican.ambient")
    val PELICAN_HURT = register("entity.pelican.hurt")
    val PELICAN_DIE = register("entity.pelican.die")

    val ALBATROSS_AMBIENT = register("entity.albatross.ambient")
    val ALBATROSS_HURT = register("entity.albatross.hurt")
    val ALBATROSS_DIE = register("entity.albatross.die")

    val DUCK_AMBIENT = register("entity.duck.ambient")
    val DUCK_HURT = register("entity.duck.hurt")
    val DUCK_DIE = register("entity.duck.die")

    val GOOSE_AMBIENT = register("entity.goose.ambient")
    val GOOSE_HURT = register("entity.goose.hurt")
    val GOOSE_DIE = register("entity.goose.die")

    val SWAN_AMBIENT = register("entity.swan.ambient")
    val SWAN_HURT = register("entity.swan.hurt")
    val SWAN_DIE = register("entity.swan.die")

    val ROOSTER_CALL = register("entity.rooster.call")
    val ROOSTER_AMBIENT = register("entity.rooster.ambient")
    val ROOSTER_HURT = register("entity.rooster.hurt")
    val ROOSTER_DIE = register("entity.rooster.die")

    val CHICK_AMBIENT = register("entity.chick.ambient")
    val CHICK_HURT = register("entity.chick.hurt")
    val CHICK_DIE = register("entity.chick.die")

    val TURKEY_AMBIENT = register("entity.turkey.ambient")
    val TURKEY_HURT = register("entity.turkey.hurt")
    val TURKEY_DIE = register("entity.turkey.die")

    val PEACOCK_AMBIENT = register("entity.peacock.ambient")
    val PEACOCK_HURT = register("entity.peacock.hurt")
    val PEACOCK_DIE = register("entity.peacock.die")

    val OSTRICH_AMBIENT = register("entity.ostrich.ambient")
    val OSTRICH_HURT = register("entity.ostrich.hurt")

    private fun register(id: String, range: Float = -1.0f): RegistryObject<SoundEvent> {
        val identifier = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, id)
        return if (range < 0)
            CommonClass.SOUND_EVENTS.register(id) { SoundEvent.createVariableRangeEvent(identifier) }
        else
            CommonClass.SOUND_EVENTS.register(id) { SoundEvent.createFixedRangeEvent(identifier, range) }
    }
}