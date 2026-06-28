package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.block.HBBlocks
import dev.hybridlabs.birds.data.HBDataGenerator.filterHybridBirds
import dev.hybridlabs.birds.effect.HBMobEffects
import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.item.HBItemGroups
import dev.hybridlabs.birds.item.HBItems
import dev.hybridlabs.birds.sound.HBSoundEvents
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
            HBItems.PUFFIN.get() to "Raw Puffin",
            HBItems.DUCK.get() to "Raw Duck",
            HBItems.GOOSE.get() to "Raw Goose",
            HBItems.TURKEY.get() to "Raw Turkey",
            HBItems.TURDUCKEN.get() to "Raw Turducken",
            HBItems.COOKED_PUFFIN.get() to "Cooked Puffin",
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
            HBMobEffects.ROOSTERS_CALLING.get() to "Roosters Calling"
        ).forEach { (effect, translation) ->
            val identifier = BuiltInRegistries.MOB_EFFECT.getKey(effect)
            builder.add("effect.${identifier?.namespace}.${identifier?.path}", translation)
        }
        
        //Sound Events
        mapOf(
            HBSoundEvents.ALBATROSS_AMBIENT to "Albatross squawks",
            HBSoundEvents.ALBATROSS_HURT to "Albatross hurts",
            HBSoundEvents.ALBATROSS_DIE to "Albatross dies",

            HBSoundEvents.SEAGULL_AMBIENT to "Seagull squawks",
            HBSoundEvents.SEAGULL_HURT to "Seagull hurts",
            HBSoundEvents.SEAGULL_DIE to "Seagull dies",

            HBSoundEvents.PELICAN_AMBIENT to "Pelican grunts",
            HBSoundEvents.PELICAN_HURT to "Pelican hurts",
            HBSoundEvents.PELICAN_DIE to "Pelican dies",

            HBSoundEvents.PUFFIN_AMBIENT to "Puffin puffs",
            HBSoundEvents.PUFFIN_HURT to "Puffin hurts",
            HBSoundEvents.PUFFIN_DIE to "Puffin dies",

            HBSoundEvents.DUCK_AMBIENT to "Duck quacks",
            HBSoundEvents.DUCK_HURT to "Duck hurts",
            HBSoundEvents.DUCK_DIE to "Duck dies",

            HBSoundEvents.GOOSE_AMBIENT to "Goose honks",
            HBSoundEvents.GOOSE_HURT to "Goose hurts",
            HBSoundEvents.GOOSE_DIE to "Goose dies",

            HBSoundEvents.SWAN_AMBIENT to "Swan trumpets",
            HBSoundEvents.SWAN_HURT to "Swan hurts",
            HBSoundEvents.SWAN_DIE to "Swan dies",

            HBSoundEvents.TURKEY_AMBIENT to "Turkey gobbles",
            HBSoundEvents.TURKEY_HURT to "Turkey hurts",
            HBSoundEvents.TURKEY_DIE to "Turkey dies",

            HBSoundEvents.ROOSTER_AMBIENT to "Rooster clucks",
            HBSoundEvents.ROOSTER_HURT to "Rooster hurts",
            HBSoundEvents.ROOSTER_DIE to "Rooster dies",
            HBSoundEvents.ROOSTER_CALL to "Rooster calls",

            HBSoundEvents.PEACOCK_AMBIENT to "Peacock calls",
            HBSoundEvents.PEACOCK_HURT to "Peacock hurts",
            HBSoundEvents.PEACOCK_DIE to "Peacock dies",
            
        ).forEach { (soundEvent, translation) ->
            builder.add(Util.makeDescriptionId("subtitles", soundEvent.get().location), translation)
        }

    }

    private fun generateEntities(builder: TranslationBuilder) {
        val entityNameMap = mapOf<EntityType<*>, String>(
            HBEntityTypes.DUCK.get() to "Duck",
            HBEntityTypes.GOOSE.get() to "Goose",
            HBEntityTypes.SWAN.get() to "Swan",
            HBEntityTypes.ROOSTER.get() to "Rooster",
            HBEntityTypes.TURKEY.get() to "Turkey",
            HBEntityTypes.PEACOCK.get() to "Peacock",
            HBEntityTypes.GUINEA_FOWL.get() to "Guinea Fowl",
            HBEntityTypes.PUFFIN.get() to "Puffin",
            HBEntityTypes.SEAGULL.get() to "Seagull",
            HBEntityTypes.ALBATROSS.get() to "Albatross",
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
