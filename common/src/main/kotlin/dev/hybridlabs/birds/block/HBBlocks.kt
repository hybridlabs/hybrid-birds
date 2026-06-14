package dev.hybridlabs.birds.block

import dev.hybridlabs.birds.CommonClass
import dev.hybridlabs.birds.platform.registration.RegistryObject
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.PushReaction
import java.util.function.Supplier

/**
 * The registry of all blocks in Hybrid Aquatic.
 */
object HBBlocks {

    val TURDUCKEN = register(
        "turducken"
    ) {
        TurduckenBlock(
            BlockBehaviour.Properties.of()
                .strength(0.5f)
                .forceSolidOn()
                .pushReaction(PushReaction.DESTROY)
                .sound(SoundType.WOOL)
        )
    }

    private fun register(id: String, block: Supplier<Block>): RegistryObject<Block> {
        return CommonClass.BLOCKS.register(id, block)
    }
}
