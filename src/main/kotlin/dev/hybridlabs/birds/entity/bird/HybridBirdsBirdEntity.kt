package dev.hybridlabs.birds.entity.bird

import net.minecraft.block.BlockState
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityPose
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.pathing.MobNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.passive.StriderEntity
import net.minecraft.registry.tag.FluidTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis")
open class HybridBirdsBirdEntity(type: EntityType<out HybridBirdsBirdEntity>, world: World) :
    AnimalEntity(type, world),
    GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var birdNavigation = MobNavigation(this, world)

    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? {
        return null
    }

    init {
        moveControl = MoveControl(this)
        navigation = this.birdNavigation
    }

    fun isBelowWaterline(): Boolean {
        return this.isSubmergedInWater || this.getFluidHeight(FluidTags.WATER) > this.getWaterline()
    }

    open fun getWaterline(): Float {
        return 0.4f
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this, "Walk/Swim/Fly/Idle", 4
            ) { state: AnimationState<HybridBirdsBirdEntity> ->
                when {
                    state.isMoving && isOnGround -> state.setAndContinue(DefaultAnimations.WALK)
                    state.isMoving && isTouchingWater -> state.setAndContinue(DefaultAnimations.SWIM)
                    !state.isMoving && isTouchingWater -> state.setAndContinue(WATER_IDLE)
                    !this.isOnGround && !isTouchingWater -> state.setAndContinue(DefaultAnimations.FLY)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun getActiveEyeHeight(pose: EntityPose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.85f
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

    override fun fall(heightDifference: Double, onGround: Boolean, state: BlockState, landedPosition: BlockPos) {}

    override fun tickMovement() {
        super.tickMovement()
        val vec3d = this.velocity
        if (!this.isOnGround && vec3d.y < 0.0) {
            this.velocity = vec3d.multiply(1.0, 0.6, 1.0)
        }
    }

    companion object {
        val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")

        @Suppress("UNUSED_PARAMETER")
        fun canBirdSpawn(
            type: EntityType<out HybridBirdsBirdEntity>,
            world: WorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            isLightLevelValidForNaturalSpawn(world, pos)
            return true
        }

        @Suppress("UNUSED_PARAMETER")
        fun canAquaticBirdSpawn(
            type: EntityType<out HybridBirdsBirdEntity>,
            world: WorldAccess,
            spawnReason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val mutable = pos.mutableCopy()
            do {
                mutable.move(Direction.UP)
            } while (world.getFluidState(mutable).isIn(FluidTags.WATER))
            return world.getBlockState(mutable).isAir
        }
    }
}