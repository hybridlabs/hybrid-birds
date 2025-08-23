package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.entity.bird.*
import dev.hybridlabs.birds.platform.Services
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier

object HybridBirdsEntityTypes {
    val ROOSTER = registerBird(
        "rooster",
        ::RoosterEntity,
        EntityDimensions.fixed(0.5f, 0.7f),
        RoosterEntity.createMobAttributes()
    )

    val CHICK = registerBird(
        "chick",
        ::ChickEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        ChickEntity.createMobAttributes()
    )

    val DUCKLING = registerBird(
        "duckling",
        ::DucklingEntity,
        EntityDimensions.fixed(0.25f, 0.4f),
        DucklingEntity.createMobAttributes()
    )

    val GOSLING = registerBird(
        "gosling",
        ::GoslingEntity,
        EntityDimensions.fixed(0.25f, 0.4f),
        GoslingEntity.createMobAttributes()
    )

    val CYGNET = registerBird(
        "cygnet",
        ::CygnetEntity,
        EntityDimensions.fixed(0.25f, 0.4f),
        CygnetEntity.createMobAttributes()
    )

    val POULT = registerBird(
        "poult",
        ::PoultEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        PoultEntity.createMobAttributes()
    )

    val PEACHICK = registerBird(
        "peachick",
        ::PeachickEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        PeachickEntity.createMobAttributes()
    )

    val KEET = registerBird(
        "keet",
        ::KeetEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        KeetEntity.createMobAttributes()
    )

    val TURKEY = registerBird(
        "turkey",
        ::TurkeyEntity,
        EntityDimensions.fixed(0.65f, 0.75f),
        TurkeyEntity.createMobAttributes()
    )

    val PEACOCK = registerBird(
        "peacock",
        ::PeacockEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        PeacockEntity.createMobAttributes()
    )

    val GUINEA_FOWL = registerBird(
        "guinea_fowl",
        ::GuineaFowlEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        GuineaFowlEntity.createMobAttributes()
    )

    val DUCK = registerBird(
        "duck",
        ::DuckEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        DuckEntity.createMobAttributes()
    )

    val GOOSE = registerBird(
        "goose",
        ::GooseEntity,
        EntityDimensions.fixed(0.6f, 1.0f),
        GooseEntity.createMobAttributes()
    )

    val SWAN = registerBird(
        "swan",
        ::SwanEntity,
        EntityDimensions.fixed(0.8f, 1.5f),
        SwanEntity.createMobAttributes()
    )

    private fun <T : LivingEntity> registerBird(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: AttributeSupplier.Builder
    ): EntityType<T> {
        return registerCustomMobCategory(id, entityFactory, dimensions, attributeContainer, MobCategory.CREATURE)
    }

    /**
     * Registers a living entity to the entity type registry with a Hybrid Aquatic spawn group.
     */
    private fun <T : LivingEntity> registerCustomMobCategory(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: AttributeSupplier.Builder,
        spawnGroup: MobCategory
    ): EntityType<T> {
        return Services.ENTITY.registerLiving(id, entityFactory, dimensions, attributeContainer, spawnGroup)
    }
}
