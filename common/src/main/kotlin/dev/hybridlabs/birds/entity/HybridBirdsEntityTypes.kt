package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.CommonClass
import dev.hybridlabs.birds.entity.bird.*
import dev.hybridlabs.birds.platform.ClientServices
import dev.hybridlabs.birds.platform.Services
import dev.hybridlabs.birds.platform.registration.RegistryObject
import dev.hybridlabs.birds.render.entity.*
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import java.util.concurrent.Callable


object HybridBirdsEntityTypes {
    @JvmStatic
    fun load() { }

    val ROOSTER = registerBird(
        "rooster",
        ::RoosterEntity,
        EntityDimensions.fixed(0.5f, 0.7f),
        RoosterEntity::createMobAttributes,
        ::RoosterEntityRenderer
    )

    val CHICK = registerBird(
        "chick",
        ::ChickEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        ChickEntity::createMobAttributes,
        ::ChickEntityRenderer
    )

    val DUCKLING = registerBird(
        "duckling",
        ::DucklingEntity,
        EntityDimensions.fixed(0.25f, 0.4f),
        DucklingEntity::createMobAttributes,
        ::DucklingEntityRenderer
    )

    val GOSLING = registerBird(
        "gosling",
        ::GoslingEntity,
        EntityDimensions.fixed(0.25f, 0.4f),
        GoslingEntity::createMobAttributes,
        ::GoslingEntityRenderer
    )

    val CYGNET = registerBird(
        "cygnet",
        ::CygnetEntity,
        EntityDimensions.fixed(0.25f, 0.4f),
        CygnetEntity::createMobAttributes,
        ::CygnetEntityRenderer
    )

    val POULT = registerBird(
        "poult",
        ::PoultEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        PoultEntity::createMobAttributes,
        ::PoultEntityRenderer
    )

    val PEACHICK = registerBird(
        "peachick",
        ::PeachickEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        PeachickEntity::createMobAttributes,
        ::PeachickEntityRenderer
    )

    val KEET = registerBird(
        "keet",
        ::KeetEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        KeetEntity::createMobAttributes,
        ::KeetEntityRenderer
    )

    val TURKEY = registerBird(
        "turkey",
        ::TurkeyEntity,
        EntityDimensions.fixed(0.65f, 0.75f),
        TurkeyEntity::createMobAttributes,
        ::TurkeyEntityRenderer
    )

    val PEACOCK = registerBird(
        "peacock",
        ::PeacockEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        PeacockEntity::createMobAttributes,
        ::PeacockEntityRenderer
    )

    val GUINEA_FOWL = registerBird(
        "guinea_fowl",
        ::GuineaFowlEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        GuineaFowlEntity::createMobAttributes,
        ::GuineaFowlEntityRenderer
    )

    val DUCK = registerBird(
        "duck",
        ::DuckEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        DuckEntity::createMobAttributes,
        ::DuckEntityRenderer
    )

    val GOOSE = registerBird(
        "goose",
        ::GooseEntity,
        EntityDimensions.fixed(0.6f, 1.0f),
        GooseEntity::createMobAttributes,
        ::GooseEntityRenderer
    )

    val SWAN = registerBird(
        "swan",
        ::SwanEntity,
        EntityDimensions.fixed(0.8f, 1.5f),
        SwanEntity::createMobAttributes,
        ::SwanEntityRenderer
    )

    private fun <T : LivingEntity> registerBird(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        entityRenderer: EntityRendererProvider<T>
    ): RegistryObject<EntityType<T>>? {
        return registerCustomMobCategory(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            MobCategory.CREATURE,
            entityRenderer
        )
    }

    /**
     * Registers a living entity to the entity type registry with a Hybrid Aquatic spawn group.
     */
    private fun <T : LivingEntity> registerCustomMobCategory(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,

        attributeContainer: Callable<AttributeSupplier.Builder>,
        spawnGroup: MobCategory,
        entityRenderer: EntityRendererProvider<T>
    ): RegistryObject<EntityType<T>>? {
        return registerLiving(id, entityFactory, dimensions, attributeContainer, spawnGroup, entityRenderer)
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
        entityRenderer: EntityRendererProvider<T>
    ): RegistryObject<EntityType<T>>? {

        val entityType = EntityType.Builder.of(entityFactory, spawnGroup).sized(dimensions.width, dimensions.height)
        return register(id, entityType, attributeContainer, entityRenderer)
    }

    /**
     * Registers an entity type to the entity type registry.
     */
    private fun <T : LivingEntity> register(
        id: String,
        entity: EntityType.Builder<T>,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        entityRenderer: EntityRendererProvider<T>
    ): RegistryObject<EntityType<T>>? {
        return CommonClass.ENTITY_TYPES.register(id) {
            val entityType = entity.build(id);
            Services.ATTRIBUTE.register(id, entityType, attributeContainer);
            ClientServices.RENDERER.register(entityType, entityRenderer)
            entityType
        }
    }
}