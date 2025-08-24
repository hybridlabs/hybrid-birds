package dev.hybridlabs.birds.platform;

import dev.hybridlabs.birds.Constants;
import dev.hybridlabs.birds.platform.services.SpawnPlacementHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

public class ForgeSpawnPlacementHelper implements SpawnPlacementHelper {
    public static <E extends Entity> void registerSpawnPlacement(SpawnPlacementRegisterEvent event,
                                                                 EntityType<E> entityType,
                                                                 SpawnPlacements.Type decoratorType,
                                                                 Heightmap.Types heightMapType,
                                                                 SpawnPlacements.SpawnPredicate<E> decoratorPredicate) {
        event.register(entityType, decoratorType, heightMapType, decoratorPredicate,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
    }


    @Override
    public <T extends Mob> void register(EntityType<T> entityType, SpawnPlacements.Type decoratorType,
                                         Heightmap.Types heightMapType,
                                         SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        final ModContainer cont = ModList.get().getModContainerById(Constants.MOD_ID).orElseThrow();
        if (cont instanceof FMLModContainer fmlModContainer) {
            fmlModContainer.getEventBus().addListener((event) -> registerSpawnPlacement((SpawnPlacementRegisterEvent) event, entityType, decoratorType, heightMapType, decoratorPredicate));
        }

    }
}
