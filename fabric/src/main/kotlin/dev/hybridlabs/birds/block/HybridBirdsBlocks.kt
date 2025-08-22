package dev.hybridlabs.birds.block

import dev.hybridlabs.birds.HybridBirds
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
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
        "turducken", TurduckenBlock(
            FabricBlockSettings.create()
                .strength(0.5f)
                .solid()
                .pistonBehavior(PushReaction.DESTROY)
                .sounds(SoundType.WOOL)
        )
    )

    private fun register(id: String, block: Block): Block {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation(HybridBirds.MOD_ID, id), block)
    }
}
