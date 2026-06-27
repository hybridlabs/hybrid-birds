package dev.hybridlabs.birds.forge

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.client.render.entity.HBEntityRenderers
import dev.hybridlabs.birds.entity.SpawnRestrictionRegistry
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist

object HybridBirdsModBusEvents {
    init {
        MOD_BUS.addListener(::registerSpawnPlacements)

        runForDist(
            clientTarget = {
                HBEntityRenderers
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
            })
    }

    private fun registerSpawnPlacements(event: RegisterSpawnPlacementsEvent) {
        SpawnRestrictionRegistry.registerSpawnRestrictions()
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        Constants.LOGGER.info("Server starting...")
    }
}