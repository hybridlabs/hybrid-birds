package dev.hybridlabs.birds.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class HBConfig(
    /**
     * The version of the data stored.
     * Increase when the config needs to be reset, i.e. when new entity spawn configs are added.
     */
    val dataVersion: Int = 5,

    val entitySpawnConfig: List<EntitySpawnConfig> = EntitySpawnConfigGenerator.generate(),
) {
    companion object {
        val CODEC: Codec<HBConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("data_version").forGetter(HBConfig::dataVersion),
                EntitySpawnConfig.CODEC.listOf().fieldOf("spawn_configuration").forGetter(
                    HBConfig::entitySpawnConfig),
            ).apply(instance, ::HBConfig)
        }
    }
}