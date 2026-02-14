package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.HybridBirdsCommon
import dev.hybridlabs.birds.entity.bird.*
import dev.hybridlabs.birds.platform.Services
import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import java.util.concurrent.Callable


object HybridBirdsEntityTypes {

    val ROOSTER = registerLandBird(
        "rooster",
        ::RoosterEntity,
        EntityDimensions.fixed(0.5f, 0.7f),
        RoosterEntity::createMobAttributes
    )

    val CHICK = registerLandBird(
        "chick",
        ::ChickEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        ChickEntity::createMobAttributes
    )

    val TURKEY = registerLandBird(
        "turkey",
        ::TurkeyEntity,
        EntityDimensions.fixed(0.65f, 0.75f),
        TurkeyEntity::createMobAttributes
    )

    val PEACOCK = registerLandBird(
        "peacock",
        ::PeacockEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        PeacockEntity::createMobAttributes
    )

    val GUINEA_FOWL = registerLandBird(
        "guinea_fowl",
        ::GuineaFowlEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        GuineaFowlEntity::createMobAttributes
    )

    val DUCK = registerAquaticBird(
        "duck",
        ::DuckEntity,
        EntityDimensions.fixed(0.5f, 0.6f),
        DuckEntity::createMobAttributes
    )

    val GOOSE = registerAquaticBird(
        "goose",
        ::GooseEntity,
        EntityDimensions.fixed(0.6f, 1.0f),
        GooseEntity::createMobAttributes
    )

    val SWAN = registerAquaticBird(
        "swan",
        ::SwanEntity,
        EntityDimensions.fixed(0.8f, 1.5f),
        SwanEntity::createMobAttributes
    )

    val FLAMINGO = registerAquaticBird(
        "flamingo",
        ::FlamingoEntity,
        EntityDimensions.fixed(0.8f, 1.8f),
        FlamingoEntity::createMobAttributes
    )

    val JAY = registerLandBird(
        "jay",
        ::JayEntity,
        EntityDimensions.fixed(0.25f, 0.3f),
        JayEntity::createMobAttributes
    )

    val SEAGULL = registerAquaticBird(
        "seagull",
        ::SeagullEntity,
        EntityDimensions.fixed(0.5f, 0.6f),
        SeagullEntity::createMobAttributes
    )

    val PELICAN = registerAquaticBird(
        "pelican",
        ::PelicanEntity,
        EntityDimensions.fixed(0.6f, 0.8f),
        PelicanEntity::createMobAttributes
    )

    val HUMMINGBIRD = registerLandBird(
        "hummingbird",
        ::HummingbirdEntity,
        EntityDimensions.fixed(0.25f, 0.3f),
        HummingbirdEntity::createMobAttributes
    )

    val OSTRICH = registerLandBird(
        "ostrich",
        ::OstrichEntity,
        EntityDimensions.fixed(1.0f, 2.0f),
        OstrichEntity::createMobAttributes
    )

    val KIWI = registerLandBird(
        "kiwi",
        ::KiwiEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        KiwiEntity::createMobAttributes
    )

    private fun <T : LivingEntity> registerLandBird(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>
    ): RegistryObject<EntityType<T>> {
        return registerCustomMobCategory(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HB_LAND_BIRD"),
        )
    }

    private fun <T : LivingEntity> registerAquaticBird(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>
    ): RegistryObject<EntityType<T>> {
        return registerCustomMobCategory(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HB_WATER_BIRD"),
        )
    }

    /**
     * Registers a living entity to the entity type registry with a Hybrid Aquatic spawn group.
     */
    @Suppress("SameParameterValue")
    private fun <T : LivingEntity> registerCustomMobCategory(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        spawnGroup: MobCategory,
    ): RegistryObject<EntityType<T>> {
        return registerLiving(id, entityFactory, dimensions, attributeContainer, spawnGroup)
    }

    /**
     * Registers a living entity to the entity type registry.
     */
    private fun <T : LivingEntity> registerLiving(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        spawnGroup: MobCategory,
    ): RegistryObject<EntityType<T>> {
        val entityType = EntityType.Builder.of(entityFactory, spawnGroup).sized(dimensions.width, dimensions.height)
        return register(id, entityType, attributeContainer)
    }

    private fun <T : LivingEntity> register(
        id: String,
        entity: EntityType.Builder<T>,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return HybridBirdsCommon.ENTITY_TYPES.register(id) {
            val entityType = entity.build(id)
            Services.PLATFORM.registerAttributes(id, entityType, attributeContainer)
            entityType
        }
    }
}