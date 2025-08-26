package dev.hybridlabs.birds.platform.services;

import dev.hybridlabs.birds.platform.registration.RegistryObject;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public interface ClientPlatformHelper {
    <E extends Entity> void registerEntityRenderer(RegistryObject<EntityType<E>> entityType, EntityRendererProvider<E> entityRendererFactory);
}
