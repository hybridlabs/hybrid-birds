package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.entity.SpawnPlacements.SpawnPredicate
import net.minecraft.world.entity.SpawnPlacements.Type
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.level.levelgen.Heightmap

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

    private fun <T : Animal> registerBirdEntity(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            Type.ON_GROUND,
            predicate
        )
    }

    private fun <T : Animal> registerAquaticBirdEntity(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            Type.NO_RESTRICTIONS,
            predicate
        )
    }

    private fun <T : Mob> register(entityType: EntityType<T>, location: Type, predicate: SpawnPredicate<T>) {
        SpawnPlacements.register(entityType, location, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, predicate)
    }
}
