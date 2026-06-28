@file:Suppress("unused")

package dev.hybridlabs.birds.item

import dev.hybridlabs.birds.CommonClass
import dev.hybridlabs.birds.block.HBBlocks
import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.platform.Services
import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.SpawnEggItem
import java.util.function.Supplier

object HBItems {

    val DUCK_SPAWN_EGG = registerSpawnEgg("duck_spawn_egg", HBEntityTypes.DUCK, 0xFFFFFF, 0xFFFFFF)
    val GOOSE_SPAWN_EGG = registerSpawnEgg("goose_spawn_egg", HBEntityTypes.GOOSE, 0xFFFFFF, 0xFFFFFF)
    val SWAN_SPAWN_EGG = registerSpawnEgg("swan_spawn_egg", HBEntityTypes.SWAN, 0xFFFFFF, 0xFFFFFF)
    val ROOSTER_SPAWN_EGG = registerSpawnEgg("rooster_spawn_egg", HBEntityTypes.ROOSTER, 0xFFFFFF, 0xFFFFFF)
    val TURKEY_SPAWN_EGG = registerSpawnEgg("turkey_spawn_egg", HBEntityTypes.TURKEY, 0xFFFFFF, 0xFFFFFF)
    val PEACOCK_SPAWN_EGG = registerSpawnEgg("peacock_spawn_egg", HBEntityTypes.PEACOCK, 0xFFFFFF, 0xFFFFFF)
    val GUINEA_FOWL_SPAWN_EGG = registerSpawnEgg("guinea_fowl_spawn_egg", HBEntityTypes.GUINEA_FOWL, 0xFFFFFF, 0xFFFFFF)
    val PUFFIN_SPAWN_EGG = registerSpawnEgg("puffin_spawn_egg", HBEntityTypes.PUFFIN, 0xFFFFFF, 0xFFFFFF)
    val SEAGULL_SPAWN_EGG = registerSpawnEgg("seagull_spawn_egg", HBEntityTypes.SEAGULL, 0xFFFFFF, 0xFFFFFF)
    val ALBATROSS_SPAWN_EGG = registerSpawnEgg("albatross_spawn_egg", HBEntityTypes.ALBATROSS, 0xFFFFFF, 0xFFFFFF)
    val PELICAN_SPAWN_EGG = registerSpawnEgg("pelican_spawn_egg", HBEntityTypes.PELICAN, 0xFFFFFF, 0xFFFFFF)

    //val JAY_SPAWN_EGG = registerSpawnEgg("jay_spawn_egg", HBEntityTypes.JAY, 0xFFFFFF, 0xFFFFFF)
    //val OSTRICH_SPAWN_EGG = registerSpawnEgg("ostrich_spawn_egg", HBEntityTypes.OSTRICH, 0xFFFFFF, 0xFFFFFF)
    //val KIWI_SPAWN_EGG = registerSpawnEgg("kiwi_spawn_egg", HBEntityTypes.KIWI, 0xFFFFFF, 0xFFFFFF)
    //val HUMMINGBIRD_SPAWN_EGG = registerSpawnEgg("hummingbird_spawn_egg", HBEntityTypes.HUMMINGBIRD, 0xFFFFFF, 0xFFFFFF)
    //val FLAMINGO_SPAWN_EGG = registerSpawnEgg("flamingo_spawn_egg", HBEntityTypes.FLAMINGO, 0xFFFFFF, 0xFFFFFF)

    val DUCK_EGG = registerEgg("duck_egg", HBEntityTypes.DUCK)
    val GOOSE_EGG = registerEgg("goose_egg", HBEntityTypes.GOOSE)
    val SWAN_EGG = registerEgg("swan_egg", HBEntityTypes.SWAN)
    val TURKEY_EGG = registerEgg("turkey_egg", HBEntityTypes.TURKEY)
    val PEACOCK_EGG = registerEgg("peacock_egg", HBEntityTypes.PEACOCK)
    val GUINEA_FOWL_EGG = registerEgg("guinea_fowl_egg", HBEntityTypes.GUINEA_FOWL)

    val COOKED_EGG = register(
        "cooked_egg"
    ) {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(4)
                    .saturationModifier(0.4F)
                    .fast()
                    .build()
            )
        )
    }

    val PUFFIN = register("puffin") {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.3F)
                    .build()
            )
        )
    }

    val DUCK = register("duck") {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.3F)
                    .build()
            )
        )
    }

    val GOOSE = register("goose") {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.4F)
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
                    .saturationModifier(0.4F)
                    .build()
            )
        )
    }

    val TURDUCKEN = register("turducken") {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(5)
                    .saturationModifier(0.6F)
                    .build()
            )
        )
    }

    val COOKED_PUFFIN = register(
        "cooked_puffin"
    ) {
        Item(
            itemSettings().food(
                FoodProperties.Builder()
                    .nutrition(5)
                    .saturationModifier(0.5F)
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
                    .saturationModifier(0.6F)
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
                    .saturationModifier(0.6F)
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
                    .saturationModifier(0.8F)
                    .build()
            )
        )
    }

    val COOKED_TURDUCKEN = register(
        "cooked_turducken"
    ) {
        BlockItem(
            HBBlocks.TURDUCKEN.get(),
            itemSettings()
                .food(
                    FoodProperties.Builder()
                        .nutrition(12)
                        .saturationModifier(1.0F)
                        .build()
                )
        )
    }

    private fun <T : Item> register(id: String, item: Supplier<T>): RegistryObject<T> {
        return CommonClass.ITEMS.register(id, item)
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
        return CommonClass.ITEMS.register(id){CustomEggItem(itemSettings().stacksTo(16), type)}

    }

    private fun itemSettings(): Item.Properties {
        return Item.Properties()
    }
}