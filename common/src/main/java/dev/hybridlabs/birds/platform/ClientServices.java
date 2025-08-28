package dev.hybridlabs.birds.platform;

import static dev.hybridlabs.birds.platform.Services.load;

import dev.hybridlabs.birds.platform.services.ClientPlatformHelper;

public class ClientServices {
    public static final ClientPlatformHelper RENDERER = load(ClientPlatformHelper.class);
}
