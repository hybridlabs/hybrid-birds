package dev.hybridlabs.skies.effect

import dev.hybridlabs.skies.CommonClass
import dev.hybridlabs.skies.platform.registration.RegistryObject
import net.minecraft.world.effect.MobEffect

object HSMobEffects {

    val ROOSTERS_CALLING = register("roosters_calling", RoostersCallingMobEffect())

    private fun register(id: String, effect: MobEffect): RegistryObject<MobEffect> {
        return CommonClass.MOB_EFFECTS.register(id){effect}
    }
}