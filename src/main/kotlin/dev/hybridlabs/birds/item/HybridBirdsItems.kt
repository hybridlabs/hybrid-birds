package dev.hybridlabs.birds.item

import dev.hybridlabs.birds.HybridBirds
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.EntityType
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.mob.MobEntity
import net.minecraft.item.EggItem
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridBirdsItems {
    val DUCK_SPAWN_EGG = registerSpawnEgg("duck_spawn_egg", HybridBirdsEntityTypes.DUCK, 0x676b8d, 0xd0ccda)
    val GOOSE_SPAWN_EGG = registerSpawnEgg("goose_spawn_egg", HybridBirdsEntityTypes.GOOSE, 0x676b8d, 0xd0ccda)
    val SWAN_SPAWN_EGG = registerSpawnEgg("swan_spawn_egg", HybridBirdsEntityTypes.SWAN, 0x676b8d, 0xd0ccda)
    val ROOSTER_SPAWN_EGG = registerSpawnEgg("rooster_spawn_egg", HybridBirdsEntityTypes.ROOSTER, 0x676b8d, 0xd0ccda)
    val TURKEY_SPAWN_EGG = registerSpawnEgg("turkey_spawn_egg", HybridBirdsEntityTypes.TURKEY, 0x676b8d, 0xd0ccda)
    val PEACOCK_SPAWN_EGG = registerSpawnEgg("peacock_spawn_egg", HybridBirdsEntityTypes.PEACOCK, 0x676b8d, 0xd0ccda)

    val DUCK_EGG = register("duck_egg", EggItem(FabricItemSettings().maxCount(16)))
    val GOOSE_EGG = register("goose_egg", EggItem(FabricItemSettings().maxCount(16)))
    val SWAN_EGG = register("swan_egg", EggItem(FabricItemSettings().maxCount(16)))
    val TURKEY_EGG = register("turkey_egg", EggItem(FabricItemSettings().maxCount(16)))
    val PEACOCK_EGG = register("peacock_egg", EggItem(FabricItemSettings().maxCount(16)))
    val GUINEA_FOWL_EGG = register("guinea_fowl_egg", EggItem(FabricItemSettings().maxCount(16)))

    val RAW_DUCK = register(
        "raw_duck", Item(
            FabricItemSettings().food(
                    FoodComponent.Builder().hunger(3).saturationModifier(0.6F).meat().build()
            )
        )
    )

    val RAW_GOOSE = register(
        "raw_goose", Item(
            FabricItemSettings().food(
                FoodComponent.Builder().hunger(4).saturationModifier(0.6F).meat().build()
            )
        )
    )

    val RAW_TURKEY = register(
        "raw_turkey", Item(
            FabricItemSettings().food(
                FoodComponent.Builder().hunger(5).saturationModifier(0.6F).meat().build()
            )
        )
    )

    val COOKED_DUCK = register(
        "cooked_duck", Item(
            FabricItemSettings().food(
                FoodComponent.Builder().hunger(12).saturationModifier(0.8F).meat().build()
            )
        )
    )

    val COOKED_GOOSE = register(
        "cooked_goose", Item(
            FabricItemSettings().food(
                FoodComponent.Builder().hunger(12).saturationModifier(0.8F).meat().build()
            )
        )
    )

    val COOKED_TURKEY = register(
        "cooked_turkey", Item(
            FabricItemSettings().food(
                FoodComponent.Builder().hunger(12).saturationModifier(0.8F).meat().build()
            )
        )
    )

    private fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(HybridBirds.MOD_ID, id), item)
    }

    private fun <T : MobEntity> registerSpawnEgg(
        id: String,
        type: EntityType<T>,
        primaryColor: Int,
        secondaryColor: Int
    ): Item {
        return register(id, SpawnEggItem(type, primaryColor, secondaryColor, FabricItemSettings()))
    }
}
