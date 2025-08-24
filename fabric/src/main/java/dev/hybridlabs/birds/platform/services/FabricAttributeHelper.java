package dev.hybridlabs.birds.platform.services;

import dev.hybridlabs.birds.platform.registration.RegistryObject;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;


public class FabricAttributeHelper implements AttributeHelper {
    @Override
    public <T extends LivingEntity> void register(@NotNull String id, EntityType<T> entityType,
                                                  Callable<AttributeSupplier.Builder> attributeContainer) {
        try {
            FabricDefaultAttributeRegistry.register(entityType, attributeContainer.call());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
