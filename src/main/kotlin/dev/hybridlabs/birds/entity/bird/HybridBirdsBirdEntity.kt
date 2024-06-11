package dev.hybridlabs.birds.entity.bird

import net.minecraft.block.BlockState
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityPose
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.FlightMoveControl
import net.minecraft.entity.ai.goal.EscapeDangerGoal
import net.minecraft.entity.ai.goal.FlyGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.ai.goal.TemptGoal
import net.minecraft.entity.ai.goal.WanderAroundGoal
import net.minecraft.entity.ai.pathing.BirdNavigation
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.ItemTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis")
open class HybridBirdsBirdEntity(
    type: EntityType<out HybridBirdsBirdEntity>,
    world: World,
) : AnimalEntity(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun initGoals() {
        goalSelector.add(0, EscapeDangerGoal(this, 0.75))
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(2, FlyGoal(this, 1.0))
        goalSelector.add(1, WanderAroundGoal(this, 0.6))
        goalSelector.add(1, TemptGoal(this, 0.6, Ingredient.fromTag(ItemTags.FISHES), false))
    }

    override fun createNavigation(world: World?): EntityNavigation {
        val birdNavigation = BirdNavigation(this, world)
        birdNavigation.setCanPathThroughDoors(false)
        birdNavigation.setCanSwim(true)
        birdNavigation.setCanEnterOpenDoors(true)
        return birdNavigation
    }

    override fun createChild(world: ServerWorld?, entity: PassiveEntity?): PassiveEntity? {
        return null
    }

    init {
        moveControl = FlightMoveControl(this, 10, false)
    }

    override fun fall(heightDifference: Double, onGround: Boolean, state: BlockState?, landedPosition: BlockPos?) {
    }

    open fun isInAir(): Boolean {
        return !this.isOnGround && !this.isTouchingWater
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "controller",
                0,
                ::predicate
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isOnGround) {
            event.controller.setAnimation(IDLE_ANIMATION)
            return PlayState.CONTINUE
        }
        if (isInAir()) {
            event.controller.setAnimation(FLY_ANIMATION)
            return PlayState.CONTINUE
        }
        if (isTouchingWater) {
            event.controller.setAnimation(SWIM_ANIMATION)
            return PlayState.CONTINUE
        }
        return PlayState.STOP
    }

    override fun getActiveEyeHeight(pose: EntityPose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.65f
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !hasCustomName()
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    companion object {
        val IDLE_ANIMATION: RawAnimation = RawAnimation.begin().then("idle", Animation.LoopType.LOOP)
        val SWIM_ANIMATION: RawAnimation = RawAnimation.begin().then("swim", Animation.LoopType.LOOP)
        val FLY_ANIMATION: RawAnimation = RawAnimation.begin().then("fly", Animation.LoopType.LOOP)

        @Suppress("UNUSED_PARAMETER")
        fun canSpawn(
            type: EntityType<out AnimalEntity>,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            return true
        }
    }
}
