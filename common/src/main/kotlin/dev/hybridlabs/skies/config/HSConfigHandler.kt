package dev.hybridlabs.skies.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import java.io.File

class HSConfigHandler(val file: File, val defaultConfig: HSConfig = HSConfig()) {
    val backupFile = file.parentFile.resolve("${file.name}-backup")

    var config: HSConfig = defaultConfig

    /**
     * Reads the config from the config file.
     * @return a data result of the config
     */
    fun read(): DataResult<Pair<HSConfig, JsonElement>> {
        val json = file.reader().use(JsonParser::parseReader)
        return HSConfig.CODEC.decode(JsonOps.INSTANCE, json)
    }

    /**
     * Reads and saves to memory the current config from its file.
     * @return whether the config was loaded successfully
     */
    fun load(): Boolean {
        val dataResult = read()
        val result = dataResult.result()

        if (!result.isPresent) {
            return false
        }

        config = result.get().first
        return true
    }

    fun save(): Boolean {
        val dataResult = HSConfig.CODEC.encodeStart(JsonOps.INSTANCE, config)
        val result = dataResult.result()

        if (!result.isPresent) {
            return false
        }

        val json = result.get()
        val text = GSON.toJson(json)
        file.writeText(text)
        return true
    }

    /**
     * Backs up the config file.
     * @return whether the backup was successful
     */
    fun backup(): Boolean {
        return runCatching {
            backupFile.writeBytes(file.readBytes())
        }.getOrNull() != null
    }

    companion object {
        val GSON: Gson = GsonBuilder().setPrettyPrinting().create()
    }
}