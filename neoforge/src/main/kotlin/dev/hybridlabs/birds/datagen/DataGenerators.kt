package dev.hybridlabs.birds.datagen

import dev.hybridlabs.birds.CommonClass
import dev.hybridlabs.birds.ConfigHelper.initializeConfig
import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.utils.NaughtyRegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.MobSpawnSettings
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.common.world.BiomeModifier
import net.neoforged.neoforge.common.world.BiomeModifiers
import net.neoforged.neoforge.data.event.GatherDataEvent
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys.BIOME_MODIFIERS

/**
 * Datagen for Forge specific data resources like biome modifiers.
 *
 * The rest of the generated resources are imported from the output of the Fabric project's runDatagen task.
 */
@Suppress("Unused", "UnusedExpression")
@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
object DataGenerators {

    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {

        val generator = event.generator
        val packOutput = generator.packOutput

        val builder = NaughtyRegistrySetBuilder()
        val lookupProvider = event.lookupProvider

        builder.add(BIOME_MODIFIERS)
        { context ->
            registerBiomeSpawns(context)
        }

        generator.addProvider(
            event.includeServer(),
            DatapackBuiltinEntriesProvider(
                packOutput,
                lookupProvider,
                builder,
                setOf(Constants.MOD_ID)
            )
        )
    }

    /**
     * Create Forge biome modifiers to add mob spawns based on the config.
     */
    private fun registerBiomeSpawns(
        context: BootstrapContext<BiomeModifier>,
    ) {
        val configHandler = initializeConfig(CommonClass.CONFIG_FILE)
        val biomeRegistry = context.lookup(Registries.BIOME)
        for (spawnConfig in configHandler.defaultConfig.entitySpawnConfig) {

            val location = "${spawnConfig.type.toShortString()}_${spawnConfig.biomes.location.path}"
            val key = ResourceKey.create(
                BIOME_MODIFIERS, CommonClass.locate(location)
            )

            context.register(
                key, BiomeModifiers.AddSpawnsBiomeModifier(
                    biomeRegistry.get(spawnConfig.biomes).get(),
                    listOf(
                        MobSpawnSettings.SpawnerData(
                            spawnConfig.type,
                            spawnConfig.weight,
                            spawnConfig.minGroupSize,
                            spawnConfig.maxGroupSize
                        )
                    )
                )
            )
        }
    }
}