package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.entity.bird.HBAquaticBirdEntity
import dev.hybridlabs.birds.entity.bird.HBBirdEntity
import dev.hybridlabs.birds.entity.bird.HBParrotEntity
import dev.hybridlabs.birds.entity.bird.HBRatiteEntity
import dev.hybridlabs.birds.entity.bird.HBWadingBirdEntity
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
            HybridBirdsEntityTypes.SEAGULL.get(),
            HybridBirdsEntityTypes.PELICAN.get(),
        ).forEach { registerAquaticBird(it) }

        setOf(
            HybridBirdsEntityTypes.FLAMINGO.get(),
        ).forEach { registerWadingBird(it) }

        setOf(
            HybridBirdsEntityTypes.JAY.get(),
            HybridBirdsEntityTypes.HUMMINGBIRD.get(),
        ).forEach { registerParrot(it) }
    }

    private fun <T : HBParrotEntity> registerParrot(entityType: EntityType<T>) {
        registerBirdEntity(entityType, HBParrotEntity::canBirdSpawn)
    }

    private fun <T : HBBirdEntity> registerTerrestrialBird(entityType: EntityType<T>) {
        registerBirdEntity(entityType, HBBirdEntity::canBirdSpawn)
    }

    private fun <T : HBRatiteEntity> registerRatite(entityType: EntityType<T>) {
        registerBirdEntity(entityType, HBRatiteEntity::canRatiteSpawn)
    }

    private fun <T : HBAquaticBirdEntity> registerAquaticBird(entityType: EntityType<T>) {
        registerAquaticBirdEntity(entityType, HBAquaticBirdEntity::canAquaticBirdSpawn)
    }

    private fun <T : HBWadingBirdEntity> registerWadingBird(entityType: EntityType<T>) {
        registerAquaticBirdEntity(entityType, HBWadingBirdEntity::canWadingBirdSpawn)
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
        SpawnPlacements.register(entityType, location, Heightmap.Types.WORLD_SURFACE, predicate)
    }
}
