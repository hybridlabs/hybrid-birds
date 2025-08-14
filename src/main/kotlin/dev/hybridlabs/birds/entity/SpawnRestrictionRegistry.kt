package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
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
            HybridBirdsEntityTypes.POULT,
            HybridBirdsEntityTypes.PEACHICK,
            HybridBirdsEntityTypes.KEET,
            HybridBirdsEntityTypes.TURKEY,
            HybridBirdsEntityTypes.PEACOCK,
            HybridBirdsEntityTypes.GUINEA_FOWL
        ).forEach { registerTerrestrialBird(it) }

        setOf(
            HybridBirdsEntityTypes.DUCKLING,
            HybridBirdsEntityTypes.GOSLING,
            HybridBirdsEntityTypes.CYGNET,
            HybridBirdsEntityTypes.DUCK,
            HybridBirdsEntityTypes.GOOSE,
            HybridBirdsEntityTypes.SWAN,
        ).forEach { registerAquaticBird(it) }
    }

    private fun <T : HybridBirdsBirdEntity> registerTerrestrialBird(entityType: EntityType<T>) {
        registerBirdEntity(entityType, HybridBirdsBirdEntity::canBirdSpawn)
    }

    private fun <T : HybridBirdsBirdEntity> registerAquaticBird(entityType: EntityType<T>) {
        registerAquaticBirdEntity(entityType, HybridBirdsBirdEntity::canAquaticBirdSpawn)
    }

    private fun <T : AnimalEntity> registerBirdEntity(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            SpawnRestriction.Location.ON_GROUND,
            predicate
        )
    }

    private fun <T : AnimalEntity> registerAquaticBirdEntity(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
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
