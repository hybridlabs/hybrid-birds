package dev.hybridlabs.birds.platform.services;

import dev.hybridlabs.birds.platform.registration.RegistryObject;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;


public class FabricClientPlatformHelper implements ClientPlatformHelper {

    @Override
    public <E extends Entity> void registerEntityRenderer(RegistryObject<EntityType<E>> entityType, EntityRendererProvider<E> entityRendererFactory) {
        EntityRendererRegistry.register(entityType.get(), entityRendererFactory);
    }
}
