package dev.hybridlabs.birds.item

import dev.hybridlabs.birds.HybridBirds
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity
import net.minecraft.item.Item
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridBirdsItems {

    val DUCK_SPAWN_EGG = registerSpawnEgg("duck_spawn_egg", HybridBirdsEntityTypes.DUCK, 0x676b8d, 0xd0ccda)
    val SWAN_SPAWN_EGG = registerSpawnEgg("swan_spawn_egg", HybridBirdsEntityTypes.SWAN, 0x676b8d, 0xd0ccda)
    val ROOSTER_SPAWN_EGG = registerSpawnEgg("rooster_spawn_egg", HybridBirdsEntityTypes.ROOSTER, 0x676b8d, 0xd0ccda)
    val TURKEY_SPAWN_EGG = registerSpawnEgg("turkey_spawn_egg", HybridBirdsEntityTypes.TURKEY, 0x676b8d, 0xd0ccda)

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