package dev.hybridlabs.birds.entity.bird

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.pathfinder.BlockPathTypes
import org.jetbrains.annotations.Nullable
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis")
open class HBBirdEntity(
    type: EntityType<out HBBirdEntity>,
    world: Level
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
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(0, PanicGoal(this, 1.2))
        goalSelector.addGoal(2, WaterAvoidingRandomStrollGoal(this, 1.0))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(5, FollowParentGoal(this, 1.1))
        goalSelector.addGoal(11, LookAtPlayerGoal(this, Player::class.java, 10.0f))
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this, "Walk/Swim/Fly/Idle", 4
            ) { state: AnimationState<HBBirdEntity> ->
                when {
                    state.isMoving && onGround() -> state.setAndContinue(DefaultAnimations.WALK)
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
        return !this.hasCustomName()
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
        @Suppress("UNUSED_PARAMETER")
        fun canBirdSpawn(
            type: EntityType<out HBBirdEntity>,
            level: LevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return isBrightEnoughToSpawn(level, pos) &&
                    level.getBlockState(pos.below()).`is`(BlockTags.ANIMALS_SPAWNABLE_ON)
        }
    }
}