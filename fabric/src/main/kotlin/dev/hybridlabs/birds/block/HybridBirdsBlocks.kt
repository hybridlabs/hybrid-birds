package dev.hybridlabs.birds.block

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.platform.Services
import dev.hybridlabs.birds.platform.registration.RegistrationProvider
import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.material.PushReaction

/**
 * The registry of all blocks in Hybrid Aquatic.
 */
object HybridBirdsBlocks {
    private val BLOCKS: RegistrationProvider<Block> =
        RegistrationProvider.get(BuiltInRegistries.BLOCK, Constants.MOD_ID)
    val TURDUCKEN = register(
        "turducken", TurduckenBlock(
            Services.BLOCK.blockSettings
                .strength(0.5f)
                .forceSolidOn()
                .pushReaction(PushReaction.DESTROY)
                .sound(SoundType.WOOL)
        )
    )

    private fun register(id: String, block: Block): RegistryObject<Block> {
        return BLOCKS.register(id){block}
    }
}
