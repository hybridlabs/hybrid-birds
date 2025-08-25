package dev.hybridlabs.birds.platform;

import dev.hybridlabs.birds.Constants;
import dev.hybridlabs.birds.platform.registration.RegistryObject;
import dev.hybridlabs.birds.platform.services.SpawnPlacementHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

public class ForgeSpawnPlacementHelper implements SpawnPlacementHelper {
    @Override
    public <T extends Mob> void register(RegistryObject<EntityType<T>> entityType, SpawnPlacements.Type decoratorType
            , Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        final ModContainer cont = ModList.get().getModContainerById(Constants.MOD_ID).orElseThrow();
        if (cont instanceof FMLModContainer fmlModContainer) {
            var handler = new SpawnPlacementRegistrationHandler<T>(entityType, decoratorType, heightMapType,
                    decoratorPredicate);
            fmlModContainer.getEventBus().addListener(handler::handleEvent);
        }
    }

    private record SpawnPlacementRegistrationHandler<T extends LivingEntity>(RegistryObject<EntityType<T>> type,
                                                                             SpawnPlacements.Type decoratorType,
                                                                             Heightmap.Types heightMapType,
                                                                             SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        private void handleEvent(SpawnPlacementRegisterEvent event) {
            event.register(type.get(), decoratorType, heightMapType, decoratorPredicate,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
        }
    }
}
