package dev.hybridlabs.skies.platform;

import static dev.hybridlabs.skies.platform.Services.load;

import dev.hybridlabs.skies.platform.services.ClientPlatformHelper;

public class ClientServices {
    public static final ClientPlatformHelper RENDERER = load(ClientPlatformHelper.class);
}
