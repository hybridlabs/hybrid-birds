package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.block.HybridBirdsBlocks
import dev.hybridlabs.birds.data.HybridBirdsDataGenerator.filterHybridBirds
import dev.hybridlabs.birds.effect.HybridBirdsStatusEffects
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.item.HybridBirdsItemGroups
import dev.hybridlabs.birds.item.HybridBirdsItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob

class LanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {
    override fun generateTranslations(builder: TranslationBuilder) {
        builder.add(
            BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(HybridBirdsItemGroups.HYBRID_BIRDS.get())
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Birds"
        )

        generateEntities(builder)

        mapOf(
            HybridBirdsItems.COOKED_EGG.get() to "Cooked Egg",
            HybridBirdsItems.TURKEY_EGG.get() to "Turkey Egg",
            HybridBirdsItems.PEACOCK_EGG.get() to "Peacock Egg",
            HybridBirdsItems.GUINEA_FOWL_EGG.get() to "Guinea Fowl Egg",
            HybridBirdsItems.DUCK_EGG.get() to "Duck Egg",
            HybridBirdsItems.GOOSE_EGG.get() to "Goose Egg",
            HybridBirdsItems.SWAN_EGG.get() to "Swan Egg",
            HybridBirdsItems.DUCK.get() to "Raw Duck",
            HybridBirdsItems.GOOSE.get() to "Raw Goose",
            HybridBirdsItems.TURKEY.get() to "Raw Turkey",
            HybridBirdsItems.TURDUCKEN.get() to "Raw Turducken",
            HybridBirdsItems.COOKED_DUCK.get() to "Cooked Duck",
            HybridBirdsItems.COOKED_GOOSE.get() to "Cooked Goose",
            HybridBirdsItems.COOKED_TURKEY.get() to "Cooked Turkey",
        ).forEach(builder::add)

        mapOf(
            HybridBirdsBlocks.TURDUCKEN to "Cooked Turducken"
        ).forEach { (block, translation) ->
            builder.add(block.get(), translation)
        }

        mapOf(
            HybridBirdsStatusEffects.ROOSTERS_CALLING.get() to "Roosters Calling"
        ).forEach { (effect, translation) ->
            val identifier = BuiltInRegistries.MOB_EFFECT.getKey(effect)
            builder.add("effect.${identifier?.namespace}.${identifier?.path}", translation)
        }

    }

    private fun generateEntities(builder: TranslationBuilder) {
        val entityNameMap = mapOf<EntityType<*>, String>(
            HybridBirdsEntityTypes.DUCK.get() to "Duck",
            HybridBirdsEntityTypes.GOOSE.get() to "Goose",
            HybridBirdsEntityTypes.SWAN.get() to "Swan",
            HybridBirdsEntityTypes.ROOSTER.get() to "Rooster",
            HybridBirdsEntityTypes.CHICK.get() to "Chick",
            HybridBirdsEntityTypes.TURKEY.get() to "Turkey",
            HybridBirdsEntityTypes.PEACOCK.get() to "Peacock",
            HybridBirdsEntityTypes.GUINEA_FOWL.get() to "Guinea Fowl",
            HybridBirdsEntityTypes.JAY.get() to "Jay",
            HybridBirdsEntityTypes.OSTRICH.get() to "Ostrich",
            HybridBirdsEntityTypes.KIWI.get() to "Kiwi",
            HybridBirdsEntityTypes.HUMMINGBIRD.get() to "Hummingbird",
            HybridBirdsEntityTypes.FLAMINGO.get() to "Flamingo",
            HybridBirdsEntityTypes.SEAGULL.get() to "Seagull",
            HybridBirdsEntityTypes.PELICAN.get() to "Pelican",
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
