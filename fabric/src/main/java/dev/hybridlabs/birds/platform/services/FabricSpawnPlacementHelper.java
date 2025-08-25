package dev.hybridlabs.birds.platform.services;

import dev.hybridlabs.birds.platform.registration.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class FabricSpawnPlacementHelper implements SpawnPlacementHelper{
    @Override
    public <T extends Mob> void register(RegistryObject<EntityType<T>> entityType, SpawnPlacements.Type decoratorType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        SpawnPlacements.register(entityType.get(), decoratorType, heightMapType, decoratorPredicate);
    }
}
