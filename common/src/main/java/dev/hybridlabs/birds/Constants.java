package dev.hybridlabs.birds;

import dev.hybridlabs.birds.platform.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Constants {

    public static final String MOD_ID = "hybridbirds";
    public static final String MOD_NAME = "Hybrid Birds";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final Path CONFIG_FILE =
            Services.PLATFORM.getConfigDir().resolve(MOD_ID + ".json");
}
