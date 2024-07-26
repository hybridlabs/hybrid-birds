package dev.hybridlabs.birds.entity.bird

import net.minecraft.block.BlockState
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityPose
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
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
    private var birdNavigation: EntityNavigation = createNavigation(world)

    override fun initGoals() {
        goalSelector.add(0, EscapeDangerGoal(this, 0.6))
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(1, WanderAroundGoal(this, 0.5))
        goalSelector.add(7, LookAroundGoal(this))
    }

    override fun createChild(world: ServerWorld?, entity: PassiveEntity?): PassiveEntity? {
        return null
    }

    init {
        moveControl = MoveControl(this)
        navigation = this.birdNavigation
    }

    override fun fall(heightDifference: Double, onGround: Boolean, state: BlockState?, landedPosition: BlockPos?) {
    }

    override fun tickMovement() {
        super.tickMovement()

        val vec3d = this.velocity
        if (!this.isOnGround && vec3d.y < 0.0) {
            this.velocity = vec3d.multiply(1.0, 0.6, 1.0)
        }
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
        if (event.isMoving && isOnGround) {
            event.controller.setAnimation(WALK_ANIMATION)
        } else {
            event.controller.setAnimation(IDLE_ANIMATION)
        }
        if (!isOnGround && !isTouchingWater) {
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
        return false
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_PARROT_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_PARROT_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_PARROT_AMBIENT
    }


    companion object {
        val IDLE_ANIMATION: RawAnimation = RawAnimation.begin().then("idle", Animation.LoopType.LOOP)
        val WALK_ANIMATION: RawAnimation = RawAnimation.begin().then("walk", Animation.LoopType.LOOP)
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
