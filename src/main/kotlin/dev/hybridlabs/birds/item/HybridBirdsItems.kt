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
    val DUCK_SPAWN_EGG = registerSpawnEgg("duck_spawn_egg", HybridBirdsEntityTypes.DUCK, 0x676B8D, 0xD0CCDA)
    val GOOSE_SPAWN_EGG = registerSpawnEgg("goose_spawn_egg", HybridBirdsEntityTypes.GOOSE, 0x676B8D, 0xD0CCDA)
    val SWAN_SPAWN_EGG = registerSpawnEgg("swan_spawn_egg", HybridBirdsEntityTypes.SWAN, 0x676B8D, 0xD0CCDA)
    val ROOSTER_SPAWN_EGG = registerSpawnEgg("rooster_spawn_egg", HybridBirdsEntityTypes.ROOSTER, 0x676B8D, 0xD0CCDA)
    val TURKEY_SPAWN_EGG = registerSpawnEgg("turkey_spawn_egg", HybridBirdsEntityTypes.TURKEY, 0x676B8D, 0xD0CCDA)
    val PEACOCK_SPAWN_EGG = registerSpawnEgg("peacock_spawn_egg", HybridBirdsEntityTypes.PEACOCK, 0x676B8D, 0xD0CCDA)

    val DUCK_EGG = registerEgg("duck_egg", HybridBirdsEntityTypes.DUCKLING)
    val GOOSE_EGG = registerEgg("goose_egg", HybridBirdsEntityTypes.GOSLING)
    val SWAN_EGG = registerEgg("swan_egg", HybridBirdsEntityTypes.CYGNET)
    val TURKEY_EGG = registerEgg("turkey_egg", HybridBirdsEntityTypes.POULT)
    val PEACOCK_EGG = registerEgg("peacock_egg", HybridBirdsEntityTypes.PEACHICK)
    val GUINEA_FOWL_EGG = registerEgg("guinea_fowl_egg", HybridBirdsEntityTypes.KEET)

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

    private fun <T : Item> register(id: String, item: T): T {
        return Registry.register(Registries.ITEM, Identifier(HybridBirds.MOD_ID, id), item)
    }

    private fun <T : MobEntity> registerSpawnEgg(id: String, type: EntityType<T>, primaryColor: Int, secondaryColor: Int): Item {
        return register(id, SpawnEggItem(type, primaryColor, secondaryColor, FabricItemSettings()))
    }

    private fun registerEgg(id: String, type: EntityType<*>): CustomEggItem {
        return register(id, CustomEggItem(FabricItemSettings().maxCount(16), type))
    }
}
