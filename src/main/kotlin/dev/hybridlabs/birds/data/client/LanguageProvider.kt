package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.data.HybridBirdsDataGenerator.filterHybridBirds
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.item.HybridBirdsItemGroups
import dev.hybridlabs.birds.item.HybridBirdsItems
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

        mapOf(
            HybridBirdsItems.TURKEY_EGG to "Turkey Egg",
            HybridBirdsItems.PEACOCK_EGG to "Peacock Egg",
            HybridBirdsItems.GUINEA_FOWL_EGG to "Guinea Fowl Egg",
            HybridBirdsItems.DUCK_EGG to "Duck Egg",
            HybridBirdsItems.GOOSE_EGG to "Goose Egg",
            HybridBirdsItems.SWAN_EGG to "Swan Egg",
            HybridBirdsItems.DUCK to "Raw Duck",
            HybridBirdsItems.GOOSE to "Raw Goose",
            HybridBirdsItems.TURKEY to "Raw Turkey",
            HybridBirdsItems.COOKED_DUCK to "Cooked Duck",
            HybridBirdsItems.COOKED_GOOSE to "Cooked Goose",
            HybridBirdsItems.COOKED_TURKEY to "Cooked Turkey",
        ).forEach(builder::add)
    }

    private fun generateEntities(builder: TranslationBuilder) {
        val entityNameMap = mapOf(
            HybridBirdsEntityTypes.DUCK to "Duck",
            HybridBirdsEntityTypes.GOOSE to "Goose",
            HybridBirdsEntityTypes.SWAN to "Swan",
            HybridBirdsEntityTypes.ROOSTER to "Rooster",
            HybridBirdsEntityTypes.CHICK to "Chick",
            HybridBirdsEntityTypes.DUCKLING to "Duckling",
            HybridBirdsEntityTypes.GOSLING to "Gosling",
            HybridBirdsEntityTypes.CYGNET to "Cygnet",
            HybridBirdsEntityTypes.POULT to "Poult",
            HybridBirdsEntityTypes.PEACHICK to "Peachick",
            HybridBirdsEntityTypes.KEET to "Keet",
            HybridBirdsEntityTypes.TURKEY to "Turkey",
            HybridBirdsEntityTypes.PEACOCK to "Peacock",
            HybridBirdsEntityTypes.GUINEA_FOWL to "Guinea Fowl",
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
            throw IllegalStateException("Entity to display name map does not contain ${nonPresentEntityNames.joinToString()}. Please modify ${javaClass.simpleName} accordingly.")
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
