package dev.hybridlabs.skies.entity

import dev.hybridlabs.hapi.entity.base.flying.*
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.entity.SpawnPlacements.SpawnPredicate
import net.minecraft.world.entity.SpawnPlacements.Type
import net.minecraft.world.level.levelgen.Heightmap

/**
 * Registers spawn restrictions for all entities when initialised.
 */
@Suppress("UNCHECKED_CAST")
object SpawnRestrictionRegistry {
    fun registerSpawnRestrictions() {
        setOf(
            HSEntityTypes.ROOSTER.get(),
            HSEntityTypes.TURKEY.get(),
            HSEntityTypes.PEACOCK.get(),
            HSEntityTypes.GUINEA_FOWL.get(),
        ).forEach { registerTerrestrialBird(it) }

        //setOf(
        //    HBEntityTypes.OSTRICH.get(),
        //    HBEntityTypes.KIWI.get(),
        //).forEach { registerRatite(it) }

        setOf(
            HSEntityTypes.DUCK.get(),
            HSEntityTypes.GOOSE.get(),
            HSEntityTypes.SWAN.get(),
            HSEntityTypes.PUFFIN.get(),
            HSEntityTypes.SEAGULL.get(),
            HSEntityTypes.ALBATROSS.get(),
            HSEntityTypes.PELICAN.get(),
        ).forEach { registerAquaticBird(it) }

        //setOf(
        //    HBEntityTypes.FLAMINGO.get(),
        //).forEach { registerWadingBird(it) }

        //setOf(
        //    HBEntityTypes.JAY.get(),
        //    HBEntityTypes.HUMMINGBIRD.get(),
        //).forEach { registerParrot(it) }
    }

    private fun <T : BaseFlyingAnimal> registerFlockingBird(entityType: EntityType<T>) {
        registerBirdEntity(entityType, BaseFlyingAnimal::canBirdSpawn)
    }

    private fun <T : BaseFlyingAnimal> registerParrot(entityType: EntityType<T>) {
        registerBirdEntity(entityType, BaseParrotEntity::canBirdSpawn)
    }

    private fun <T : BaseFlyingAnimal> registerTerrestrialBird(entityType: EntityType<T>) {
        registerBirdEntity(entityType, BaseFlyingAnimal::canBirdSpawn)
    }

    private fun <T : BaseRatiteEntity> registerRatite(entityType: EntityType<T>) {
        registerBirdEntity(entityType, BaseFlyingAnimal::canBirdSpawn)
    }

    private fun <T : BaseAquaticBirdEntity> registerAquaticBird(entityType: EntityType<T>) {
        registerAquaticBirdEntity(entityType, BaseAquaticBirdEntity::canAquaticBirdSpawn)
    }

    private fun <T : BaseWadingBirdEntity> registerWadingBird(entityType: EntityType<T>) {
        registerAquaticBirdEntity(entityType, BaseFlyingAnimal::canBirdSpawn)
    }

    private fun <T : BaseFlyingAnimal> registerBirdEntity(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            Type.ON_GROUND,
            predicate
        )
    }

    private fun <T : BaseFlyingAnimal> registerAquaticBirdEntity(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
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
