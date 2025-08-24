package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.platform.Services
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
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
            HybridBirdsEntityTypes.ROOSTER!!.get(),
            HybridBirdsEntityTypes.CHICK!!.get(),
            HybridBirdsEntityTypes.POULT!!.get(),
            HybridBirdsEntityTypes.PEACHICK!!.get(),
            HybridBirdsEntityTypes.KEET!!.get(),
            HybridBirdsEntityTypes.TURKEY!!.get(),
            HybridBirdsEntityTypes.PEACOCK!!.get(),
            HybridBirdsEntityTypes.GUINEA_FOWL!!.get()
        ).forEach { registerTerrestrialBird(it) }

        setOf(
            HybridBirdsEntityTypes.DUCKLING!!.get(),
            HybridBirdsEntityTypes.GOSLING!!.get(),
            HybridBirdsEntityTypes.CYGNET!!.get(),
            HybridBirdsEntityTypes.DUCK!!.get(),
            HybridBirdsEntityTypes.GOOSE!!.get(),
            HybridBirdsEntityTypes.SWAN!!.get(),
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
        Services.SPAWN_PLACEMENT.register(entityType, location, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, predicate)
    }
}
