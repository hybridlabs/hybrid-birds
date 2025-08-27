package dev.hybridlabs.birds

import dev.hybridlabs.birds.Constants.MOD_NAME
import dev.hybridlabs.birds.block.HybridBirdsBlocks
import dev.hybridlabs.birds.config.HybridBirdsConfig
import dev.hybridlabs.birds.config.HybridBirdsConfigHandler
import dev.hybridlabs.birds.effect.HybridBirdsStatusEffects
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.entity.SpawnRestrictionRegistry
import dev.hybridlabs.birds.item.HybridBirdsItemGroups
import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import dev.hybridlabs.birds.tag.HybridBirdsBiomeTags
import dev.hybridlabs.birds.tag.HybridBirdsItemTags
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import org.slf4j.Logger

@Suppress("UnusedExpression")
object HybridBirds : ModInitializer {

    private val logger: Logger = Constants.LOG


	override fun onInitialize() {

		logger.info("Initializing $MOD_NAME")
        val configFile = Constants.CONFIG_FILE
        val configHandler = HybridBirdsConfigHandler(configFile.toFile())


        HybridBirdsSoundEvents
        HybridBirdsEntityTypes

        HybridBirdsBlocks
        HybridBirdsItems
        HybridBirdsItemGroups

        HybridBirdsBiomeTags
        HybridBirdsItemTags

        HybridBirdsStatusEffects

        SpawnRestrictionRegistry

        initializeConfig(configFile, configHandler)
        registerBiomeModifications(configHandler.config)
	}


    private fun registerBiomeModifications(config: HybridBirdsConfig) {
        config.entitySpawnConfig.forEach { config ->
            BiomeModifications.addSpawn(BiomeSelectors.tag(config.biomes), config.group, config.type, config.weight, config.minGroupSize, config.maxGroupSize)
        }
    }
}
