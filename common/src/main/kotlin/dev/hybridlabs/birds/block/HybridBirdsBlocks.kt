package dev.hybridlabs.birds.block

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.platform.Services
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.material.PushReaction

/**
 * The registry of all blocks in Hybrid Aquatic.
 */
object HybridBirdsBlocks {
    val TURDUCKEN = register(
        "turducken", dev.hybridlabs.birds.block.TurduckenBlock(
            Services.BLOCK.blockSettings
                .strength(0.5f)
                .forceSolidOn()
                .pushReaction(PushReaction.DESTROY)
                .sound(SoundType.WOOL)
        )
    )

    private fun register(id: String, block: Block): Block {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation(Constants.MOD_ID, id), block)
    }
}
