@file:Suppress("unused")

package dev.hybridlabs.birds.item

import dev.hybridlabs.birds.HybridBirdsCommon
import dev.hybridlabs.birds.block.HybridBirdsBlocks
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.platform.Services
import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.SpawnEggItem
import java.util.function.Supplier

object HybridBirdsItems {

    val DUCK_SPAWN_EGG = registerSpawnEgg("duck_spawn_egg", HybridBirdsEntityTypes.DUCK, 0xcfb99a, 0x1a854b)
    val GOOSE_SPAWN_EGG = registerSpawnEgg("goose_spawn_egg", HybridBirdsEntityTypes.GOOSE, 0x3e312f, 0xdcdce7)
    val SWAN_SPAWN_EGG = registerSpawnEgg("swan_spawn_egg", HybridBirdsEntityTypes.SWAN, 0xdcdce7, 0x272736)
    val ROOSTER_SPAWN_EGG = registerSpawnEgg("rooster_spawn_egg", HybridBirdsEntityTypes.ROOSTER, 0xc08585, 0x603d68)
    val TURKEY_SPAWN_EGG = registerSpawnEgg("turkey_spawn_egg", HybridBirdsEntityTypes.TURKEY, 0x383551, 0xcc4468)
    val PEACOCK_SPAWN_EGG = registerSpawnEgg("peacock_spawn_egg", HybridBirdsEntityTypes.PEACOCK, 0x3faa73, 0x010c86)
    val GUINEA_FOWL_SPAWN_EGG = registerSpawnEgg("guinea_fowl_spawn_egg", HybridBirdsEntityTypes.GUINEA_FOWL, 0x356b97, 0x252533)
    val BLUE_JAY_SPAWN_EGG = registerSpawnEgg("blue_jay_spawn_egg", HybridBirdsEntityTypes.BLUE_JAY, 0x356b97, 0x252533)

    val DUCK_EGG = registerEgg("duck_egg", HybridBirdsEntityTypes.DUCKLING)
    val GOOSE_EGG = registerEgg("goose_egg", HybridBirdsEntityTypes.GOSLING)
    val SWAN_EGG = registerEgg("swan_egg", HybridBirdsEntityTypes.CYGNET)
    val TURKEY_EGG = registerEgg("turkey_egg", HybridBirdsEntityTypes.POULT)
    val PEACOCK_EGG = registerEgg("peacock_egg", HybridBirdsEntityTypes.PEACHICK)
    val GUINEA_FOWL_EGG = registerEgg("guinea_fowl_egg", HybridBirdsEntityTypes.KEET)

    val COOKED_EGG = register(
        "cooked_egg"
    ) {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(0.4F)
                    .meat()
                    .fast()
                    .build()
            )
        )
    }

    val DUCK = register("duck") {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.3F)
                    .meat().build()
            )
        )
    }

    val GOOSE = register("goose") {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(0.4F)
                    .meat()
                    .build()
            )
        )
    }

    val TURKEY = register(
        "turkey"
    ) {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(4)
                    .saturationMod(0.4F)
                    .meat()
                    .build()
            )
        )
    }

    val TURDUCKEN = register("turducken") {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(0.6F)
                    .meat()
                    .build()
            )
        )
    }

    val COOKED_DUCK = register(
        "cooked_duck"
    ) {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(0.6F)
                    .meat()
                    .build()
            )
        )
    }

    val COOKED_GOOSE = register(
        "cooked_goose"
    ) {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(7)
                    .saturationMod(0.6F)
                    .meat()
                    .build()
            )
        )
    }

    val COOKED_TURKEY = register(
        "cooked_turkey"
    ) {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(8)
                    .saturationMod(0.8F)
                    .meat()
                    .build()
            )
        )
    }

    val COOKED_TURDUCKEN = register(
        "cooked_turducken"
    ) {
        BlockItem(
            HybridBirdsBlocks.TURDUCKEN.get(),
            itemSettings()
                .food(
                    FoodProperties.Builder()
                        .nutrition(12)
                        .saturationMod(1.0F)
                        .meat()
                        .build()
                )
        )
    }

    private fun <T : Item> register(id: String, item: Supplier<T>): RegistryObject<T> {
        return HybridBirdsCommon.ITEMS.register(id, item)
    }

    private fun <T : Mob> registerSpawnEgg(
        id: String,
        type: RegistryObject<EntityType<T>>?,
        primaryColor: Int,
        secondaryColor: Int
    ): Supplier<SpawnEggItem> {
        return Services.PLATFORM.registerSpawnEggItem(id, { type?.get() }, primaryColor, secondaryColor)
    }

    private fun <T : Mob> registerEgg(id: String, type: RegistryObject<EntityType<T>>?): RegistryObject<Item> {
        return HybridBirdsCommon.ITEMS.register(id){CustomEggItem(itemSettings().stacksTo(16), type)}

    }

    private fun itemSettings(): Item.Properties {
        return Item.Properties()
    }
}