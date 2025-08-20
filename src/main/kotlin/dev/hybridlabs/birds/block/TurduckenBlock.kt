package dev.hybridlabs.birds.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.ShapeContext
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Property
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import net.minecraft.world.event.GameEvent

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class TurduckenBlock(settings: Settings) : Block(settings) {
    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return BITES_TO_SHAPE[(state.get(BITES) as Int)]
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        val itemStack = player.getStackInHand(hand)
        itemStack.item
        if (world.isClient) {
            if (tryEat(world, pos, state, player).isAccepted) {
                return ActionResult.SUCCESS
            }

            if (itemStack.isEmpty) {
                return ActionResult.CONSUME
            }
        }

        return tryEat(world, pos, state, player)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        return if (direction == Direction.DOWN && !state.canPlaceAt(
                world,
                pos
            )
        ) Blocks.AIR.defaultState else super.getStateForNeighborUpdate(
            state,
            direction,
            neighborState,
            world,
            pos,
            neighborPos
        )
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        return world.getBlockState(pos.down()).isSolid
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(*arrayOf<Property<*>>(BITES))
    }

    override fun getComparatorOutput(state: BlockState, world: World, pos: BlockPos): Int {
        return getComparatorOutput(state.get(BITES) as Int)
    }

    override fun hasComparatorOutput(state: BlockState): Boolean {
        return true
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return false
    }

    init {
        this.defaultState =
            (stateManager.defaultState as BlockState).with(BITES, 0) as BlockState
    }

    companion object {
        val BITES: IntProperty = IntProperty.of("bites", 0, 9)
        private val DEFAULT_COMPARATOR_OUTPUT: Int
        private val BITES_TO_SHAPE: Array<VoxelShape>

        private fun tryEat(
            world: WorldAccess,
            pos: BlockPos,
            state: BlockState,
            player: PlayerEntity
        ): ActionResult {
            if (!player.canConsume(false)) {
                return ActionResult.PASS
            }

            player.hungerManager.add(4, 0.5f)
            val bites = state[BITES]
            world.emitGameEvent(player, GameEvent.EAT, pos)

            if (world is World) {
                world.playSound(
                    null,
                    pos,
                    SoundEvents.ENTITY_FOX_EAT,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
                )
            }

            return if (bites < 9) {
                world.setBlockState(pos, state.with(BITES, bites + 1), 3)
                ActionResult.SUCCESS
            } else {
                world.removeBlock(pos, false)
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos)
                ActionResult.SUCCESS
            }
        }


        fun getComparatorOutput(bites: Int): Int {
            return (7 - bites) * 2
        }

        init {
            DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0)
            BITES_TO_SHAPE = arrayOf(
                createCuboidShape(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                createCuboidShape(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                createCuboidShape(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                createCuboidShape(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                createCuboidShape(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                createCuboidShape(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                createCuboidShape(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                createCuboidShape(3.0, 0.0, 5.0, 13.0, 8.0, 14.0),
                createCuboidShape(3.0, 0.0, 8.0, 13.0, 8.0, 14.0),
                createCuboidShape(3.0, 0.0, 11.0, 13.0, 8.0, 14.0),
            )
        }
    }
}