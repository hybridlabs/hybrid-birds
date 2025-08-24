package dev.hybridlabs.birds.platform.services;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;


public class FabricRendererHelper implements RendererHelper{

    @Override
    public <E extends Entity> void register(EntityType<E> entityType, EntityRendererProvider<E> entityRendererFactory) {
        EntityRendererRegistry.register(entityType,entityRendererFactory);
    }
}
