package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.entity.bird.HybridBirdsParrotEntity
import dev.hybridlabs.birds.entity.bird.HybridBirdsRatiteEntity
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
@Suppress("UNCHECKED_CAST")
object SpawnRestrictionRegistry {
    fun registerSpawnRestrictions() {
        setOf(
            HybridBirdsEntityTypes.ROOSTER.get(),
            HybridBirdsEntityTypes.CHICK.get(),
            HybridBirdsEntityTypes.TURKEY.get(),
            HybridBirdsEntityTypes.PEACOCK.get(),
            HybridBirdsEntityTypes.GUINEA_FOWL.get(),
        ).forEach { registerTerrestrialBird(it) }

        setOf(
            HybridBirdsEntityTypes.OSTRICH.get(),
            HybridBirdsEntityTypes.KIWI.get(),
        ).forEach { registerRatite(it) }

        setOf(
            HybridBirdsEntityTypes.DUCK.get(),
            HybridBirdsEntityTypes.GOOSE.get(),
            HybridBirdsEntityTypes.SWAN.get(),
        ).forEach { registerAquaticBird(it) }

        setOf(
            HybridBirdsEntityTypes.JAY.get(),
        ).forEach { registerParrot(it) }
    }

    private fun <T : HybridBirdsParrotEntity> registerParrot(entityType: EntityType<T>) {
        registerBirdEntity(entityType, HybridBirdsParrotEntity::canBirdSpawn)
    }

    private fun <T : HybridBirdsBirdEntity> registerTerrestrialBird(entityType: EntityType<T>) {
        registerBirdEntity(entityType, HybridBirdsBirdEntity::canBirdSpawn)
    }

    private fun <T : HybridBirdsRatiteEntity> registerRatite(entityType: EntityType<T>) {
        registerBirdEntity(entityType, HybridBirdsRatiteEntity::canBirdSpawn)
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

    private fun <T : Mob> register(
        entityType: EntityType<T>,
        location: Type,
        predicate: SpawnPredicate<T>,
    ) {
        SpawnPlacements.register(entityType, location, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, predicate)
    }
}
