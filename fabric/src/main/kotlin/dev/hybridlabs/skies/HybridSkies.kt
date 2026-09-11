package dev.hybridlabs.skies

import dev.hybridlabs.skies.block.HSBlocks
import dev.hybridlabs.skies.config.HBConfig
import dev.hybridlabs.skies.effect.HSMobEffects
import dev.hybridlabs.skies.entity.HSEntityTypes
import dev.hybridlabs.skies.entity.SpawnRestrictionRegistry
import dev.hybridlabs.skies.item.HSItemGroups
import dev.hybridlabs.skies.item.HSItems
import dev.hybridlabs.skies.sound.HSSoundEvents
import dev.hybridlabs.skies.tag.HBBiomeTags
import dev.hybridlabs.skies.tag.HBItemTags
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import org.slf4j.Logger

@Suppress("UnusedExpression")
object HybridSkies : ModInitializer {

    private val logger: Logger = Constants.LOGGER

    override fun onInitialize() {
        val configHandler = ConfigHelper.initializeConfig(CommonClass.CONFIG_FILE)
        logger.info("Initializing ${Constants.MOD_NAME}")
        CommonClass.init()

        HSSoundEvents
        HSEntityTypes

        HSBlocks
        HSItems
        HSItemGroups

        HBBiomeTags
        HBItemTags

        HSMobEffects

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
