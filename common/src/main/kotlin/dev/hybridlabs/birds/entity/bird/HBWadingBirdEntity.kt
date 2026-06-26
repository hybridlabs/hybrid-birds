package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.ai.control.BirdFloatControl
import dev.hybridlabs.birds.entity.ai.goal.BirdFollowParentGoal
import net.minecraft.core.BlockPos
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation

@Suppress("LeakingThis", "DEPRECATION")
open class HBWadingBirdEntity(
    type: EntityType<out HBWadingBirdEntity>,
    world: Level
) :
    HBBirdEntity(type, world),
    GeoEntity {

    init {
        moveControl = BirdFloatControl(this)
        navigation = AmphibiousPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, PanicGoal(this, 1.2))
        goalSelector.addGoal(1, RandomStrollGoal(this, 1.0))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(5, BirdFollowParentGoal(this, 1.1))
        goalSelector.addGoal(11, LookAtPlayerGoal(this, Player::class.java, 10.0f))
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this, "Walk/Swim/Fly/Idle", 4
            ) { state: AnimationState<HBWadingBirdEntity> ->
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
        return dimensions.height * 1.0f
    }

    override fun isAffectedByFluids(): Boolean {
        return !onGround()
    }

    override fun getWaterline(): Float {
        return 1.4f
    }

    override fun isInWater(): Boolean {
        if (!super.isInWater()) return false

        val fluidHeight = this.getFluidHeight(FluidTags.WATER)

        return fluidHeight > (this.getWaterline() - 0.2f)
    }

    override fun maxUpStep(): Float {
        return if (!super.isInWater()) 1.75f
        else 1.25f
    }

    companion object {
        val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")
    }
}