package dev.hybridlabs.birds.platform;

import dev.hybridlabs.birds.platform.services.RendererHelper;

import static dev.hybridlabs.birds.platform.Services.load;

public class ClientServices {
    public static final RendererHelper RENDERER = load(RendererHelper.class);
}
