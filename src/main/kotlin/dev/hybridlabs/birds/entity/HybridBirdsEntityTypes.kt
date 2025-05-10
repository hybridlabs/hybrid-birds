package dev.hybridlabs.birds.entity

import dev.hybridlabs.birds.HybridBirds
import dev.hybridlabs.birds.entity.bird.*
import dev.hybridlabs.birds.utils.HybridBirdsSpawnGroup
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

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
        EntityDimensions.fixed(0.25f, 0.25f),
        DucklingEntity.createMobAttributes()
    )

    val GOSLING = registerBird(
        "gosling",
        ::GoslingEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        GoslingEntity.createMobAttributes()
    )

    val CYGNET = registerBird(
        "cygnet",
        ::CygnetEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
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
        EntityDimensions.fixed(0.6f, 0.6f),
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
        EntityDimensions.fixed(0.5f, 0.7f),
        DuckEntity.createMobAttributes()
    )

    val GOOSE = registerBird(
        "goose",
        ::GooseEntity,
        EntityDimensions.fixed(0.6f, 1.2f),
        GooseEntity.createMobAttributes()
    )

    val SWAN = registerBird(
        "swan",
        ::SwanEntity,
        EntityDimensions.fixed(0.8f, 1.5f),
        SwanEntity.createMobAttributes()
    )

    @JvmStatic
    fun getChickEntityType(): EntityType<ChickEntity> {
        return CHICK
    }
    fun getDucklingEntityType(): EntityType<DucklingEntity> {
        return DUCKLING
    }
    fun getGoslingEntityType(): EntityType<GoslingEntity> {
        return GOSLING
    }
    fun getCygnetEntityType(): EntityType<CygnetEntity> {
        return CYGNET
    }
    fun getKeetEntityType(): EntityType<KeetEntity> {
        return KEET
    }
    fun getPeachickEntityType(): EntityType<PeachickEntity> {
        return PEACHICK
    }
    fun getPoultEntityType(): EntityType<PoultEntity> {
        return POULT
    }

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
