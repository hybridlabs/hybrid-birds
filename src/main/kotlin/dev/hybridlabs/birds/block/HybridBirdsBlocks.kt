package dev.hybridlabs.birds.block

import dev.hybridlabs.birds.HybridBirds
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

/**
 * The registry of all blocks in Hybrid Aquatic.
 */
object HybridBirdsBlocks {
    val TURDUCKEN = register(
        "turducken", TurduckenBlock(
            FabricBlockSettings.create()
                .strength(0.5f)
                .solid()
                .pistonBehavior(PistonBehavior.DESTROY)
                .sounds(BlockSoundGroup.WOOL)
        )
    )

    private fun register(id: String, block: Block): Block {
        return Registry.register(Registries.BLOCK, Identifier.of(HybridBirds.MOD_ID, id), block)
    }
}
