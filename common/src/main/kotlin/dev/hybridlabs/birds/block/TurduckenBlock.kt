package dev.hybridlabs.birds.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class TurduckenBlock(settings: Properties?) : Block(settings!!) {


    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return BITES_TO_SHAPE[(state.getValue(BITES) as Int)]
    }

    override fun use(
        state: BlockState,
        world: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult
    ): InteractionResult {
        val itemStack = player.getItemInHand(hand)
        itemStack.item
        if (world.isClientSide) {
            if (tryEat(world, pos, state, player).consumesAction()) {
                return InteractionResult.SUCCESS
            }

            if (itemStack.isEmpty) {
                return InteractionResult.CONSUME
            }
        }

        return tryEat(world, pos, state, player)
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        return if (direction == Direction.DOWN && !state.canSurvive(
                world,
                pos
            )
        ) Blocks.AIR.defaultBlockState() else super.updateShape(
            state,
            direction,
            neighborState,
            world,
            pos,
            neighborPos
        )
    }

    override fun canSurvive(state: BlockState, levelReader: LevelReader, pos: BlockPos): Boolean {
        return levelReader.getBlockState(pos.below()).isSolid
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(*arrayOf<Property<*>>(BITES))
    }

    override fun getAnalogOutputSignal(state: BlockState, world: Level, pos: BlockPos): Int {
        return getComparatorOutput(state.getValue(BITES) as Int)
    }

    override fun hasAnalogOutputSignal(state: BlockState): Boolean {
        return true
    }

    override fun isPathfindable(state: BlockState, world: BlockGetter, pos: BlockPos, type: PathComputationType): Boolean {
        return false
    }

    init {
        this.registerDefaultState(defaultBlockState().setValue(BITES,0))
    }

    companion object {
        val BITES: IntegerProperty = IntegerProperty.create("bites", 0, 9)
        private val DEFAULT_COMPARATOR_OUTPUT: Int
        private val BITES_TO_SHAPE: Array<VoxelShape>

        private fun tryEat(
            world: LevelAccessor,
            pos: BlockPos,
            state: BlockState,
            player: Player
        ): InteractionResult {
            if (!player.canEat(false)) {
                return InteractionResult.PASS
            }

            player.foodData.eat (4, 0.5f)
            val bites = state.getValue(BITES)
            world.gameEvent(player, GameEvent.EAT, pos)

            if (world is Level) {
                world.playSound(
                    null,
                    pos,
                    SoundEvents.FOX_EAT,
                    SoundSource.PLAYERS,
                    1.0f,
                    1.0f
                )
            }

            return if (bites < 9) {
                world.setBlock(pos, state.setValue(BITES, bites + 1), 3)
                InteractionResult.SUCCESS
            } else {
                world.removeBlock(pos, false)
                world.gameEvent(player, GameEvent.BLOCK_DESTROY, pos)
                InteractionResult.SUCCESS
            }
        }


        fun getComparatorOutput(bites: Int): Int {
            return (7 - bites) * 2
        }

        init {
            DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0)
            BITES_TO_SHAPE = arrayOf(
                box(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                box(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                box(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                box(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                box(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                box(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                box(3.0, 0.0, 2.0, 13.0, 8.0, 14.0),
                box(3.0, 0.0, 5.0, 13.0, 8.0, 14.0),
                box(3.0, 0.0, 8.0, 13.0, 8.0, 14.0),
                box(3.0, 0.0, 11.0, 13.0, 8.0, 14.0),
            )
        }
    }
}