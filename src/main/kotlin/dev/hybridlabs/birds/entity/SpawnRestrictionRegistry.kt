package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.entity.bird.BirdEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnRestriction
import net.minecraft.entity.SpawnRestriction.SpawnPredicate
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.world.Heightmap

/**
 * Registers spawn restrictions for all entities when initialised.
 */
object SpawnRestrictionRegistry {
    init {
        setOf(
            HybridBirdsEntityTypes.ROOSTER,
            HybridBirdsEntityTypes.CHICK,
            HybridBirdsEntityTypes.DUCKLING,
            HybridBirdsEntityTypes.GOSLING,
            HybridBirdsEntityTypes.CYGNET,
            HybridBirdsEntityTypes.POULT,
            HybridBirdsEntityTypes.PEACHICK,
            HybridBirdsEntityTypes.KEET,
            HybridBirdsEntityTypes.TURKEY,
            HybridBirdsEntityTypes.PEACOCK,
            HybridBirdsEntityTypes.GUINEA_FOWL,
            HybridBirdsEntityTypes.DUCK,
            HybridBirdsEntityTypes.GOOSE,
            HybridBirdsEntityTypes.SWAN,
        ).forEach { registerBird(it) }
    }

    private fun <T : AnimalEntity> registerBird(entityType: EntityType<T>) {
        registerAnimalEntity(entityType, BirdEntity::canSpawn)
    }

    private fun <T : AnimalEntity> registerAnimalEntity(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            SpawnRestriction.Location.NO_RESTRICTIONS,
            predicate
        )
    }

    private fun <T : MobEntity> register(entityType: EntityType<T>, location: SpawnRestriction.Location, predicate: SpawnPredicate<T>) {
        SpawnRestriction.register(entityType, location, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, predicate)
    }
}
