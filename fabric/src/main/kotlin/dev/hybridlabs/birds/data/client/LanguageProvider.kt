package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.block.HBBlocks
import dev.hybridlabs.birds.data.HBDataGenerator.filterHybridBirds
import dev.hybridlabs.birds.effect.HBStatusEffects
import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.item.HBItemGroups
import dev.hybridlabs.birds.item.HBItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob

class LanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {
    override fun generateTranslations(builder: TranslationBuilder) {
        builder.add(
            BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(HBItemGroups.HYBRID_BIRDS.get())
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Birds"
        )

        generateEntities(builder)

        mapOf(
            HBItems.COOKED_EGG.get() to "Cooked Egg",
            HBItems.TURKEY_EGG.get() to "Turkey Egg",
            HBItems.PEACOCK_EGG.get() to "Peacock Egg",
            HBItems.GUINEA_FOWL_EGG.get() to "Guinea Fowl Egg",
            HBItems.DUCK_EGG.get() to "Duck Egg",
            HBItems.GOOSE_EGG.get() to "Goose Egg",
            HBItems.SWAN_EGG.get() to "Swan Egg",
            HBItems.DUCK.get() to "Raw Duck",
            HBItems.GOOSE.get() to "Raw Goose",
            HBItems.TURKEY.get() to "Raw Turkey",
            HBItems.TURDUCKEN.get() to "Raw Turducken",
            HBItems.COOKED_DUCK.get() to "Cooked Duck",
            HBItems.COOKED_GOOSE.get() to "Cooked Goose",
            HBItems.COOKED_TURKEY.get() to "Cooked Turkey",
        ).forEach(builder::add)

        mapOf(
            HBBlocks.TURDUCKEN to "Cooked Turducken"
        ).forEach { (block, translation) ->
            builder.add(block.get(), translation)
        }

        mapOf(
            HBStatusEffects.ROOSTERS_CALLING.get() to "Roosters Calling"
        ).forEach { (effect, translation) ->
            val identifier = BuiltInRegistries.MOB_EFFECT.getKey(effect)
            builder.add("effect.${identifier?.namespace}.${identifier?.path}", translation)
        }

    }

    private fun generateEntities(builder: TranslationBuilder) {
        val entityNameMap = mapOf<EntityType<*>, String>(
            HBEntityTypes.DUCK.get() to "Duck",
            HBEntityTypes.GOOSE.get() to "Goose",
            HBEntityTypes.SWAN.get() to "Swan",
            HBEntityTypes.ROOSTER.get() to "Rooster",
            HBEntityTypes.CHICK.get() to "Chick",
            HBEntityTypes.TURKEY.get() to "Turkey",
            HBEntityTypes.PEACOCK.get() to "Peacock",
            HBEntityTypes.GUINEA_FOWL.get() to "Guinea Fowl",
            HBEntityTypes.SEAGULL.get() to "Seagull",
            HBEntityTypes.PELICAN.get() to "Pelican",
        )

        val nonPresentEntityNames = mutableListOf<EntityType<*>>()

        BuiltInRegistries.ENTITY_TYPE
            .filter(filterHybridBirds(BuiltInRegistries.ENTITY_TYPE))
            .forEach { type ->
                if (type.baseClass.isAssignableFrom(Mob::class.java)) {
                    if (!entityNameMap.containsKey(type)) {
                        nonPresentEntityNames.add(type)
                    }
                }
            }

        if (nonPresentEntityNames.isNotEmpty()) {
            throw IllegalStateException("Entity to display name map does not contain ${nonPresentEntityNames.joinToString()}. Please modify ${javaClass.simpleName} accordingly.")
        }

        entityNameMap.forEach { (entityType, translation) ->
            val id = BuiltInRegistries.ENTITY_TYPE.getKey(entityType)
            val translationKey = entityType.descriptionId
            val namespace = id.namespace
            val path = id.path
            builder.add(translationKey, translation)
            builder.add("item.$namespace.${path}_spawn_egg", "$translation Spawn Egg")
        }
    }
}
