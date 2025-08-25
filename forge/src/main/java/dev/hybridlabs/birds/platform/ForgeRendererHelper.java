package dev.hybridlabs.birds.platform;

import dev.hybridlabs.birds.Constants;
import dev.hybridlabs.birds.platform.registration.RegistryObject;
import dev.hybridlabs.birds.platform.services.RendererHelper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;


public class ForgeRendererHelper implements RendererHelper {

    @Override
    @OnlyIn(Dist.CLIENT)
    public <E extends Entity> void register(RegistryObject<EntityType<E>> entityType, EntityRendererProvider<E> entityRendererFactory) {
        final ModContainer cont = ModList.get().getModContainerById(Constants.MOD_ID).orElseThrow();
        if (cont instanceof FMLModContainer fmlModContainer) {
            var handler = new ForgeRendererHelper.RendererRegistrationHandler<E>(entityType, entityRendererFactory);
            fmlModContainer.getEventBus().addListener(handler::handleEvent);
        }
    }

    private record RendererRegistrationHandler<T extends Entity>(RegistryObject<EntityType<T>> type,
                                                                 EntityRendererProvider<T> rendererProvider) {
        private void handleEvent(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(type.get(), rendererProvider);
        }
    }

}
