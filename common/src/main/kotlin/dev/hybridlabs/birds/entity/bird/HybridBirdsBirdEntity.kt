package dev.hybridlabs.birds.entity.bird

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.state.BlockState
import org.jetbrains.annotations.Nullable
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis")
open class HybridBirdsBirdEntity(
    type: EntityType<out HybridBirdsBirdEntity>,
    world: Level,
    open val isAquatic: Boolean,
) :
    Animal(type, world),
    GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var birdNavigation = GroundPathNavigation(this, world)

    @Nullable
    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
        throw NotImplementedError("Breeding is not implemented")
    }


    init {
        moveControl = MoveControl(this)
        navigation = this.birdNavigation
    }

    fun isBelowWaterline(): Boolean {
        return this.isUnderWater || this.getFluidHeight(FluidTags.WATER) > this.getWaterline()
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
                    state.isMoving && onGround() -> state.setAndContinue(DefaultAnimations.WALK)
                    this.isAquatic && state.isMoving && isInWater -> state.setAndContinue(DefaultAnimations.SWIM)
                    this.isAquatic && !state.isMoving && isInWater -> state.setAndContinue(WATER_IDLE)
                    !this.onGround() && !isInWater -> state.setAndContinue(DefaultAnimations.FLY)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.85f
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return false
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.PARROT_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.PARROT_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.PARROT_AMBIENT
    }

    override fun checkFallDamage(heightDifference: Double, onGround: Boolean, state: BlockState, landedPosition: BlockPos) {}

    override fun aiStep() {
        super.aiStep()
        val vec3d = this.deltaMovement
        if (!this.onGround() && vec3d.y < 0.0) {
            this.deltaMovement = vec3d.multiply(1.0, 0.6, 1.0)
        }
    }

    companion object {
        val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")

        @Suppress("UNUSED_PARAMETER")
        fun canBirdSpawn(
            type: EntityType<out HybridBirdsBirdEntity>,
            level: LevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            isBrightEnoughToSpawn(level, pos)
            return true
        }

        @Suppress("UNUSED_PARAMETER")
        fun canAquaticBirdSpawn(
            type: EntityType<out HybridBirdsBirdEntity>,
            level: LevelAccessor,
            spawnReason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val mutable = pos.mutable()
            do {
                mutable.move(Direction.UP)
            } while (level.getFluidState(mutable).`is`(FluidTags.WATER))
            return level.getBlockState(mutable).isAir
        }
    }
}