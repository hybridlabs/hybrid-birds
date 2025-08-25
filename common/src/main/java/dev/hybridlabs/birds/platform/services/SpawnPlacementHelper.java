package dev.hybridlabs.birds.platform.services;

import dev.hybridlabs.birds.platform.registration.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public interface SpawnPlacementHelper {
    <T extends Mob> void register(RegistryObject<EntityType<T>> entityType, SpawnPlacements.Type decoratorType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate);
}