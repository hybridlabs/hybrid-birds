package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.platform.Services
import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.SpawnPlacements.SpawnPredicate
import net.minecraft.world.entity.SpawnPlacements.Type
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.level.levelgen.Heightmap

/**
 * Registers spawn restrictions for all entities when initialised.
 */
@Suppress("UNCHECKED_CAST")
object SpawnRestrictionRegistry {
    @JvmStatic
    fun load() { }

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
        ).forEach { registerTerrestrialBird(it as RegistryObject<EntityType<HybridBirdsBirdEntity>>) }

        setOf(
            HybridBirdsEntityTypes.DUCKLING,
            HybridBirdsEntityTypes.GOSLING,
            HybridBirdsEntityTypes.CYGNET,
            HybridBirdsEntityTypes.DUCK,
            HybridBirdsEntityTypes.GOOSE,
            HybridBirdsEntityTypes.SWAN,
        ).forEach { registerAquaticBird(it as RegistryObject<EntityType<HybridBirdsBirdEntity>>) }
    }

    private fun <T : HybridBirdsBirdEntity> registerTerrestrialBird(entityType: RegistryObject<EntityType<T>>) {
        registerBirdEntity(entityType, HybridBirdsBirdEntity::canBirdSpawn)
    }

    private fun <T : HybridBirdsBirdEntity> registerAquaticBird(entityType: RegistryObject<EntityType<T>>) {
        registerAquaticBirdEntity(entityType, HybridBirdsBirdEntity::canAquaticBirdSpawn)
    }

    private fun <T : Animal> registerBirdEntity(entityType: RegistryObject<EntityType<T>>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            Type.ON_GROUND,
            predicate
        )
    }

    private fun <T : Animal> registerAquaticBirdEntity(entityType: RegistryObject<EntityType<T>>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            Type.NO_RESTRICTIONS,
            predicate
        )
    }

    private fun <T : Mob> register(entityType: RegistryObject<EntityType<T>>, location: Type, predicate: SpawnPredicate<T>) {
        Services.SPAWN_PLACEMENT.register(entityType, location, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, predicate)
    }
}
