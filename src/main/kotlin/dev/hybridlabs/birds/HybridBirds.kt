package dev.hybridlabs.birds

import dev.hybridlabs.aquatic.config.HybridBirdsConfigHandler
import dev.hybridlabs.birds.config.HybridBirdsConfig
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
import net.fabricmc.loader.api.FabricLoader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Path
import kotlin.io.path.notExists

object HybridBirds : ModInitializer {
    const val MOD_ID = "hybrid-birds"
    private const val MOD_NAME = "Hybrid Birds"

    private val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    private val configFile: Path = FabricLoader.getInstance().configDir.resolve("$MOD_ID.json")
    private val configHandler = HybridBirdsConfigHandler(configFile.toFile())

	override fun onInitialize() {
		logger.info("Initializing $MOD_NAME")

        HybridBirdsSoundEvents
        HybridBirdsEntityTypes

        HybridBirdsItems
        HybridBirdsItemGroups

        HybridBirdsBiomeTags
        HybridBirdsItemTags

        HybridBirdsStatusEffects

        SpawnRestrictionRegistry

        initializeConfig()

        registerBiomeModifications(configHandler.config)
	}

    private fun initializeConfig() {
        if (configFile.notExists()) {
            logger.info("$MOD_NAME config file did not exist, creating one")
            configHandler.save()
        } else {
            logger.info("Loading $MOD_NAME config file")
            configHandler.load()

            // check config data version, if updated then reset
            val defaultConfig = configHandler.defaultConfig
            val config = configHandler.config
            if (config.dataVersion < defaultConfig.dataVersion) {
                logger.info("Old $MOD_NAME config file found, upgrading")

                configHandler.backup()

                configHandler.config = defaultConfig
                configHandler.save()

                logger.info("$MOD_NAME config reset, the old config has been backed up to \"${configHandler.backupFile}\"")
            }
        }
    }


    private fun registerBiomeModifications(config: HybridBirdsConfig) {
        config.entitySpawnConfig.forEach { config ->
            BiomeModifications.addSpawn(BiomeSelectors.tag(config.biomes), config.group, config.type, config.weight, config.minGroupSize, config.maxGroupSize)
        }
    }
}
