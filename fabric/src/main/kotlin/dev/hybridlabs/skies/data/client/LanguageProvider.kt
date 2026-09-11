package dev.hybridlabs.skies.data.client

import dev.hybridlabs.skies.block.HSBlocks
import dev.hybridlabs.skies.data.HBDataGenerator.filterHybridBirds
import dev.hybridlabs.skies.effect.HSMobEffects
import dev.hybridlabs.skies.entity.HSEntityTypes
import dev.hybridlabs.skies.item.HSItemGroups
import dev.hybridlabs.skies.item.HSItems
import dev.hybridlabs.skies.sound.HSSoundEvents
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.Util
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import java.util.concurrent.CompletableFuture

class LanguageProvider( output: FabricDataOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>) : FabricLanguageProvider(output,lookupProvider) {
    override fun generateTranslations(lookupProvider: HolderLookup.Provider, builder: TranslationBuilder) {
        builder.add(
            BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(HSItemGroups.HYBRID_BIRDS.get())
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Birds"
        )

        generateEntities(builder)

        mapOf(
            HSItems.COOKED_EGG.get() to "Cooked Egg",
            HSItems.TURKEY_EGG.get() to "Turkey Egg",
            HSItems.PEACOCK_EGG.get() to "Peacock Egg",
            HSItems.GUINEA_FOWL_EGG.get() to "Guinea Fowl Egg",
            HSItems.DUCK_EGG.get() to "Duck Egg",
            HSItems.GOOSE_EGG.get() to "Goose Egg",
            HSItems.SWAN_EGG.get() to "Swan Egg",
            HSItems.PUFFIN.get() to "Raw Puffin",
            HSItems.DUCK.get() to "Raw Duck",
            HSItems.GOOSE.get() to "Raw Goose",
            HSItems.TURKEY.get() to "Raw Turkey",
            HSItems.TURDUCKEN.get() to "Raw Turducken",
            HSItems.COOKED_PUFFIN.get() to "Cooked Puffin",
            HSItems.COOKED_DUCK.get() to "Cooked Duck",
            HSItems.COOKED_GOOSE.get() to "Cooked Goose",
            HSItems.COOKED_TURKEY.get() to "Cooked Turkey",
        ).forEach(builder::add)

        mapOf(
            HSBlocks.TURDUCKEN to "Cooked Turducken"
        ).forEach { (block, translation) ->
            builder.add(block.get(), translation)
        }

        mapOf(
            HSMobEffects.ROOSTERS_CALLING.get() to "Roosters Calling"
        ).forEach { (effect, translation) ->
            val identifier = BuiltInRegistries.MOB_EFFECT.getKey(effect)
            builder.add("effect.${identifier?.namespace}.${identifier?.path}", translation)
        }
        
        //Sound Events
        mapOf(
            HSSoundEvents.ALBATROSS_AMBIENT to "Albatross squawks",
            HSSoundEvents.ALBATROSS_HURT to "Albatross hurts",
            HSSoundEvents.ALBATROSS_DIE to "Albatross dies",

            HSSoundEvents.SEAGULL_AMBIENT to "Seagull squawks",
            HSSoundEvents.SEAGULL_HURT to "Seagull hurts",
            HSSoundEvents.SEAGULL_DIE to "Seagull dies",

            HSSoundEvents.PELICAN_AMBIENT to "Pelican grunts",
            HSSoundEvents.PELICAN_HURT to "Pelican hurts",
            HSSoundEvents.PELICAN_DIE to "Pelican dies",

            HSSoundEvents.PUFFIN_AMBIENT to "Puffin puffs",
            HSSoundEvents.PUFFIN_HURT to "Puffin hurts",
            HSSoundEvents.PUFFIN_DIE to "Puffin dies",

            HSSoundEvents.DUCK_AMBIENT to "Duck quacks",
            HSSoundEvents.DUCK_HURT to "Duck hurts",
            HSSoundEvents.DUCK_DIE to "Duck dies",

            HSSoundEvents.GOOSE_AMBIENT to "Goose honks",
            HSSoundEvents.GOOSE_HURT to "Goose hurts",
            HSSoundEvents.GOOSE_DIE to "Goose dies",

            HSSoundEvents.SWAN_AMBIENT to "Swan trumpets",
            HSSoundEvents.SWAN_HURT to "Swan hurts",
            HSSoundEvents.SWAN_DIE to "Swan dies",

            HSSoundEvents.TURKEY_AMBIENT to "Turkey gobbles",
            HSSoundEvents.TURKEY_HURT to "Turkey hurts",
            HSSoundEvents.TURKEY_DIE to "Turkey dies",

            HSSoundEvents.ROOSTER_AMBIENT to "Rooster clucks",
            HSSoundEvents.ROOSTER_HURT to "Rooster hurts",
            HSSoundEvents.ROOSTER_DIE to "Rooster dies",
            HSSoundEvents.ROOSTER_CALL to "Rooster calls",

            HSSoundEvents.PEACOCK_AMBIENT to "Peacock calls",
            HSSoundEvents.PEACOCK_HURT to "Peacock hurts",
            HSSoundEvents.PEACOCK_DIE to "Peacock dies",
            
        ).forEach { (soundEvent, translation) ->
            builder.add(Util.makeDescriptionId("subtitles", soundEvent.get().location), translation)
        }

    }

    private fun generateEntities(builder: TranslationBuilder) {
        val entityNameMap = mapOf<EntityType<*>, String>(
            HSEntityTypes.DUCK.get() to "Duck",
            HSEntityTypes.GOOSE.get() to "Goose",
            HSEntityTypes.SWAN.get() to "Swan",
            HSEntityTypes.ROOSTER.get() to "Rooster",
            HSEntityTypes.TURKEY.get() to "Turkey",
            HSEntityTypes.PEACOCK.get() to "Peacock",
            HSEntityTypes.GUINEA_FOWL.get() to "Guinea Fowl",
            HSEntityTypes.PUFFIN.get() to "Puffin",
            HSEntityTypes.SEAGULL.get() to "Seagull",
            HSEntityTypes.ALBATROSS.get() to "Albatross",
            HSEntityTypes.PELICAN.get() to "Pelican",
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
