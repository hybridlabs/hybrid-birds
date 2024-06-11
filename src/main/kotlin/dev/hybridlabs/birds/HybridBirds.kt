package dev.hybridlabs.birds

import dev.hybridlabs.birds.entity.SpawnRestrictionRegistry
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.world.EntityBiomeModifications
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object HybridBirds : ModInitializer {
    const val MOD_ID = "hybrid-birds"
    const val MOD_NAME = "Hybrid Birds"

    private val logger: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		logger.info("Initializing $MOD_NAME")

        HybridBirdsEntityTypes
        EntityBiomeModifications

        SpawnRestrictionRegistry
	}
}
