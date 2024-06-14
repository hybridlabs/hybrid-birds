package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.HybridBirds
import dev.hybridlabs.birds.entity.bird.DuckEntity
import dev.hybridlabs.birds.entity.bird.HummingbirdEntity
import dev.hybridlabs.birds.entity.bird.SeagullEntity
import dev.hybridlabs.birds.utils.HybridBirdsSpawnGroup
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridBirdsEntityTypes {
    val SEAGULL = registerBird(
        "seagull",
        ::SeagullEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        SeagullEntity.createMobAttributes()
    )

    val DUCK = registerBird(
        "duck",
        ::DuckEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        DuckEntity.createMobAttributes()
    )

    val HUMMINGBIRD = registerBird(
        "hummingbird",
        ::HummingbirdEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        HummingbirdEntity.createMobAttributes()
    )



    private fun <T : LivingEntity> registerBird(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridBirdsSpawnGroup.BIRDS)
    }

    /**
     * Registers a living entity to the entity type registry with a Hybrid Aquatic spawn group.
     */
    private fun <T : LivingEntity> registerCustomSpawnGroup(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder,
        hybridBirdsSpawnGroup: HybridBirdsSpawnGroup
    ): EntityType<T> {
        return registerLiving(id, entityFactory, dimensions, attributeContainer, hybridBirdsSpawnGroup.spawnGroup)
    }

    /**
     * Registers a living entity to the entity type registry.
     */
    private fun <T : LivingEntity> registerLiving(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder,
        spawnGroup: SpawnGroup
    ): EntityType<T> {
        val entityType = FabricEntityTypeBuilder.create(spawnGroup, entityFactory).dimensions(dimensions).build()
        FabricDefaultAttributeRegistry.register(entityType, attributeContainer)
        return register(id, entityType)
    }

    /**
     * Registers an entity type to the entity type registry.
     */
    private fun <T : Entity> register(id: String, entity: EntityType<T>): EntityType<T> {
        return Registry.register(Registries.ENTITY_TYPE, Identifier(HybridBirds.MOD_ID, id), entity)
    }
}