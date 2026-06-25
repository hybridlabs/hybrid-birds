package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.ai.control.BirdFloatControl
import dev.hybridlabs.birds.entity.ai.control.BirdFlyFloatControl
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation

@Suppress("LeakingThis")
open class HBAquaticBirdEntity(
    type: EntityType<out HBAquaticBirdEntity>,
    world: Level
) :
    HBBirdEntity(type, world),
    GeoEntity {

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = BirdFloatControl(this)
        navigation = AmphibiousPathNavigation(this, level)
        lookControl = LookControl(this)

        return AmphibiousPathNavigation(this, level)
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, PanicGoal(this, 1.2))
        goalSelector.addGoal(1, RandomStrollGoal(this, 1.0))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(5, FollowParentGoal(this, 1.1))
        goalSelector.addGoal(11, LookAtPlayerGoal(this, Player::class.java, 10.0f))
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(DefaultAnimations.genericLivingController(this))

        controllerRegistrar.add(
            AnimationController(
                this, "Walk/Swim/Fly/Idle", 4
            ) { state: AnimationState<HBAquaticBirdEntity> ->
                when {
                    state.isMoving && onGround() -> state.setAndContinue(DefaultAnimations.WALK)
                    state.isMoving && isInWater -> state.setAndContinue(DefaultAnimations.SWIM)
                    !state.isMoving && isInWater -> state.setAndContinue(WATER_IDLE)
                    !this.onGround() && !isInWater -> state.setAndContinue(DefaultAnimations.FLY)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.85f
    }

    companion object {
        val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")

        @Suppress("UNUSED_PARAMETER")
        fun canAquaticBirdSpawn(
            type: EntityType<out HBAquaticBirdEntity>,
            level: LevelAccessor,
            spawnReason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val mutable = pos.mutable()
            do {
                mutable.move(Direction.UP)
            } while (level.getFluidState(mutable).`is`(FluidTags.WATER))
            return level.getBlockState(mutable).isAir
        }
    }
}