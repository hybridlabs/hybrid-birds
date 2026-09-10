package dev.hybridlabs.skies

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.skies.Constants.MOD_NAME
import dev.hybridlabs.skies.block.HSBlocks
import dev.hybridlabs.skies.config.HSConfig
import dev.hybridlabs.skies.config.HSConfigHandler
import dev.hybridlabs.skies.effect.HSMobEffects
import dev.hybridlabs.skies.entity.HSEntityTypes
import dev.hybridlabs.skies.entity.SpawnRestrictionRegistry
import dev.hybridlabs.skies.item.HSItemGroups
import dev.hybridlabs.skies.item.HSItems
import dev.hybridlabs.skies.sound.HSSoundEvents
import dev.hybridlabs.skies.tag.HSBiomeTags
import dev.hybridlabs.skies.tag.HSItemTags
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import org.slf4j.Logger

@Suppress("UnusedExpression")
object HybridSkies : ModInitializer {

    private val logger: Logger = Constants.LOG


	override fun onInitialize() {

		logger.info("Initializing $MOD_NAME")
        val configFile = Constants.CONFIG_FILE
        val configHandler = HSConfigHandler(configFile.toFile())


        HSSoundEvents
        HSEntityTypes

        HSBlocks
        HSItems
        HSItemGroups

        HSBiomeTags
        HSItemTags

        HSMobEffects

        SpawnRestrictionRegistry

        initializeConfig(configFile, configHandler)
        registerBiomeModifications(configHandler.config)
	}


    private fun registerBiomeModifications(config: HSConfig) {
        config.entitySpawnConfig.forEach { config ->
            BiomeModifications.addSpawn(BiomeSelectors.tag(config.biomes), config.group, config.type, config.weight, config.minGroupSize, config.maxGroupSize)
        }
    }
}
