package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.data.HybridBirdsDataGenerator.filterHybridBirds
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.item.HybridBirdsItemGroups
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity
import net.minecraft.registry.Registries

class LanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {
    override fun generateTranslations(builder: TranslationBuilder) {
        builder.add(
            Registries.ITEM_GROUP.getKey(HybridBirdsItemGroups.HYBRID_BIRDS)
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Birds"
        )

        generateEntities(builder)
    }

    private fun generateEntities(builder: TranslationBuilder) {
        val entityNameMap = mapOf(
            HybridBirdsEntityTypes.DUCK to "Duck",
            HybridBirdsEntityTypes.SWAN to "Swan",
            HybridBirdsEntityTypes.ROOSTER to "Rooster",
            HybridBirdsEntityTypes.CHICK to "Chick",
            HybridBirdsEntityTypes.TURKEY to "Turkey",
        )

        val nonPresentEntityNames = mutableListOf<EntityType<*>>()

        Registries.ENTITY_TYPE
            .filter(filterHybridBirds(Registries.ENTITY_TYPE))
            .forEach { type ->
                if (type.baseClass.isAssignableFrom(MobEntity::class.java)) {
                    if (!entityNameMap.containsKey(type)) {
                        nonPresentEntityNames.add(type)
                    }
                }
            }

        if (nonPresentEntityNames.isNotEmpty()) {
            throw throw IllegalStateException("Entity to display name map does not contain ${nonPresentEntityNames.joinToString()}. Please modify ${javaClass.simpleName} accordingly.")
        }

        entityNameMap.forEach { (entityType, translation) ->
            val id = Registries.ENTITY_TYPE.getId(entityType)
            val translationKey = entityType.translationKey
            val namespace = id.namespace
            val path = id.path
            builder.add(translationKey, translation)
            builder.add("item.$namespace.${path}_spawn_egg", "$translation Spawn Egg")
        }
    }
}