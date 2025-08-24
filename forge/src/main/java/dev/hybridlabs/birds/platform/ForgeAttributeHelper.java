package dev.hybridlabs.birds.platform;

import dev.hybridlabs.birds.Constants;
import dev.hybridlabs.birds.platform.services.AttributeHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

public class ForgeAttributeHelper implements AttributeHelper {

    @Override
    public <T extends LivingEntity> void register(@NotNull String id, EntityType<T> entityType,
                                                  Callable<AttributeSupplier.Builder> attributeContainer) {
        final ModContainer cont = ModList.get().getModContainerById(Constants.MOD_ID).orElseThrow();
        if (cont instanceof FMLModContainer fmlModContainer) {
            var handler = new AttributeRegistrationHandler(id, entityType, attributeContainer);
            fmlModContainer.getEventBus().addListener(handler::handleEvent);
        }
    }

    private record  AttributeRegistrationHandler(String id,
                                                 EntityType<? extends LivingEntity> type,
                                                 Callable<AttributeSupplier.Builder> supplier) {
        private void handleEvent(EntityAttributeCreationEvent event){
            try {
                event.put(type,supplier.call().build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
