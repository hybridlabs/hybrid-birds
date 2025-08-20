@file:Suppress("unused")

package dev.hybridlabs.birds.item

import dev.hybridlabs.birds.HybridBirds
import dev.hybridlabs.birds.block.HybridBirdsBlocks
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridBirdsItems {
    val DUCK_SPAWN_EGG = registerSpawnEgg("duck_spawn_egg", HybridBirdsEntityTypes.DUCK, 0xcfb99a, 0x4c2239)
    val GOOSE_SPAWN_EGG = registerSpawnEgg("goose_spawn_egg", HybridBirdsEntityTypes.GOOSE, 0xdcdce7, 0x3e312f)
    val SWAN_SPAWN_EGG = registerSpawnEgg("swan_spawn_egg", HybridBirdsEntityTypes.SWAN, 0xdcdce7, 0x272736)
    val ROOSTER_SPAWN_EGG = registerSpawnEgg("rooster_spawn_egg", HybridBirdsEntityTypes.ROOSTER, 0xa6555f, 0x603d68)
    val TURKEY_SPAWN_EGG = registerSpawnEgg("turkey_spawn_egg", HybridBirdsEntityTypes.TURKEY, 0x2c283c, 0xcc4468)
    val PEACOCK_SPAWN_EGG = registerSpawnEgg("peacock_spawn_egg", HybridBirdsEntityTypes.PEACOCK, 0x3faa73, 0x010c86)
    val GUINEA_FOWL_SPAWN_EGG = registerSpawnEgg("guinea_fowl_spawn_egg", HybridBirdsEntityTypes.GUINEA_FOWL, 0x356b97, 0x252533)

    val DUCK_EGG = registerEgg("duck_egg", HybridBirdsEntityTypes.DUCKLING)
    val GOOSE_EGG = registerEgg("goose_egg", HybridBirdsEntityTypes.GOSLING)
    val SWAN_EGG = registerEgg("swan_egg", HybridBirdsEntityTypes.CYGNET)
    val TURKEY_EGG = registerEgg("turkey_egg", HybridBirdsEntityTypes.POULT)
    val PEACOCK_EGG = registerEgg("peacock_egg", HybridBirdsEntityTypes.PEACHICK)
    val GUINEA_FOWL_EGG = registerEgg("guinea_fowl_egg", HybridBirdsEntityTypes.KEET)

    val COOKED_EGG = register(
        "cooked_egg", Item(
            FabricItemSettings().food(
                FoodComponent.Builder()
                    .hunger(4)
                    .saturationModifier(0.4F)
                    .meat()
                    .snack()
                    .build()
            )
        )
    )

    val DUCK = register(
        "duck", Item(
            FabricItemSettings().food(
                FoodComponent.Builder()
                    .hunger(2)
                    .saturationModifier(0.3F)
                    .meat().build()
            )
        )
    )

    val GOOSE = register(
        "goose", Item(
            FabricItemSettings().food(
                FoodComponent.Builder()
                    .hunger(3)
                    .saturationModifier(0.4F)
                    .meat()
                    .build()
            )
        )
    )

    val TURKEY = register(
        "turkey", Item(
            FabricItemSettings().food(
                FoodComponent.Builder()
                    .hunger(4)
                    .saturationModifier(0.4F)
                    .meat()
                    .build()
            )
        )
    )

    val TURDUCKEN = register(
        "turducken", Item(
            FabricItemSettings().food(
                FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.6F)
                    .meat()
                    .build()
            )
        )
    )

    val COOKED_DUCK = register(
        "cooked_duck", Item(
            FabricItemSettings().food(
                FoodComponent.Builder()
                    .hunger(6)
                    .saturationModifier(0.6F)
                    .meat()
                    .build()
            )
        )
    )

    val COOKED_GOOSE = register(
        "cooked_goose", Item(
            FabricItemSettings().food(
                FoodComponent.Builder()
                    .hunger(7)
                    .saturationModifier(0.6F)
                    .meat()
                    .build()
            )
        )
    )

    val COOKED_TURKEY = register(
        "cooked_turkey", Item(
            FabricItemSettings().food(
                FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(0.8F)
                    .meat()
                    .build()
            )
        )
    )

    val COOKED_TURDUCKEN = register(
        "cooked_turducken", BlockItem(
            HybridBirdsBlocks.TURDUCKEN,
            FabricItemSettings()
                .food(
                FoodComponent.Builder()
                    .hunger(12)
                    .saturationModifier(1.0F)
                    .meat()
                    .build()
            )
        )
    )

    private fun <T : Item> register(id: String, item: T): T {
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

    private fun registerEgg(id: String, type: EntityType<*>): CustomEggItem {
        return register(id, CustomEggItem(FabricItemSettings().maxCount(16), type))
    }
}