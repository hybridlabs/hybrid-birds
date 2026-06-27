package dev.hybridlabs.birds

import dev.hybridlabs.birds.ConfigHelper.initializeConfig
import dev.hybridlabs.birds.Constants.MOD_NAME
import dev.hybridlabs.birds.block.HBBlocks
import dev.hybridlabs.birds.config.HBConfig
import dev.hybridlabs.birds.effect.HBMobEffects
import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.entity.SpawnRestrictionRegistry
import dev.hybridlabs.birds.item.HBItemGroups
import dev.hybridlabs.birds.item.HBItems
import dev.hybridlabs.birds.sound.HBSoundEvents
import dev.hybridlabs.birds.tag.HBBiomeTags
import dev.hybridlabs.birds.tag.HBItemTags
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import org.slf4j.Logger

@Suppress("UnusedExpression")
object HybridBirds : ModInitializer {

    private val logger: Logger = Constants.LOGGER

    override fun onInitialize() {
        val configHandler = ConfigHelper.initializeConfig(CommonClass.CONFIG_FILE)
        logger.info("Initializing ${Constants.MOD_NAME}")
        CommonClass.init()

        HBSoundEvents
        HBEntityTypes

        HBBlocks
        HBItems
        HBItemGroups

        HBBiomeTags
        HBItemTags

        HBMobEffects

        SpawnRestrictionRegistry.registerSpawnRestrictions()

        registerBiomeModifications(configHandler.config)
	}

    private fun registerBiomeModifications(config: HBConfig) {
        config.entitySpawnConfig.forEach { config ->
            BiomeModifications.addSpawn(
                BiomeSelectors.tag(config.biomes),
                config.group,
                config.type,
                config.weight,
                config.minGroupSize,
                config.maxGroupSize
            )
        }
    }
}
