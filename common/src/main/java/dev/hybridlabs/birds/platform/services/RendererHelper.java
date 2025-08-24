package dev.hybridlabs.birds.platform.services;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public interface RendererHelper {
    <E extends Entity> void register(EntityType<E> entityType, EntityRendererProvider<E> entityRendererFactory);
}
