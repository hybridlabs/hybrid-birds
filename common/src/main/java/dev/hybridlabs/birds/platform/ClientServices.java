package dev.hybridlabs.birds.platform;

import dev.hybridlabs.birds.platform.services.ClientPlatformHelper;

import static dev.hybridlabs.birds.platform.Services.load;

public class ClientServices {
    public static final ClientPlatformHelper RENDERER = load(ClientPlatformHelper.class);
}
