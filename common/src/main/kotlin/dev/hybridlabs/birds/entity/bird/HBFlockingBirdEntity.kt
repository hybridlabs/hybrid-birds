package dev.hybridlabs.birds.entity.bird

import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level
import software.bernie.geckolib.animatable.GeoEntity

open class HBFlockingBirdEntity(
    type: EntityType<out HBFlockingBirdEntity>,
    world: Level,
) :
    HBBirdEntity(type, world),
    GeoEntity {
}