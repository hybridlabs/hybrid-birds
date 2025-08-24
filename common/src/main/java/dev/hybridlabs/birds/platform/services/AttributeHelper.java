package dev.hybridlabs.birds.platform.services;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

public interface AttributeHelper {
    <T extends LivingEntity> void register(@NotNull String id, EntityType<T> entityType,
                                                Callable<AttributeSupplier.Builder> attributeContainer);
}
