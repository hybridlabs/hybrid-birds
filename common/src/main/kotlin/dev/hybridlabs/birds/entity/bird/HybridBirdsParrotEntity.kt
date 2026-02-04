package dev.hybridlabs.birds.entity.bird

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.FlyingMoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.animal.Parrot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import org.jetbrains.annotations.Nullable
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis")
open class HybridBirdsParrotEntity(
    type: EntityType<out HybridBirdsBirdEntity>,
    world: Level,
) :
    HybridBirdsBirdEntity(type, world, false),
    GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var birdNavigation = FlyingPathNavigation(this, world)
    var flap: Float = 0f
    var flapSpeed: Float = 0f
    var oFlapSpeed: Float = 0f
    var oFlap: Float = 0f
    private var flapping = 1.0f
    private var nextFlap = 1.0f

    @Nullable
    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
        throw NotImplementedError("Breeding is not implemented")
    }

    override fun registerGoals() {
        this.goalSelector.addGoal(0, PanicGoal(this, 1.25))
        this.goalSelector.addGoal(0, FloatGoal(this))
        this.goalSelector.addGoal(1, LookAtPlayerGoal(this, Player::class.java, 8.0f))
        this.goalSelector.addGoal(3, FollowMobGoal(this, 1.0, 3.0f, 7.0f))
    }

    private fun calculateFlapping() {
        this.oFlap = this.flap
        this.oFlapSpeed = this.flapSpeed
        this.flapSpeed += (if (!this.onGround() && !this.isPassenger) 4 else -1).toFloat() * 0.3f
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0f, 1.0f)
        if (!this.onGround() && this.flapping < 1.0f) {
            this.flapping = 1.0f
        }

        this.flapping *= 0.9f
        val vec3: Vec3 = this.deltaMovement
        if (!this.onGround() && vec3.y < 0.0) {
            this.deltaMovement = vec3.multiply(1.0, 0.6, 1.0)
        }

        this.flap += this.flapping * 2.0f
    }

    override fun isFlapping(): Boolean {
        return this.flyDist > this.nextFlap
    }

    override fun onFlap() {
        this.playSound(SoundEvents.PARROT_FLY, 0.15f, 1.0f)
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0f
    }

    init {
        moveControl = FlyingMoveControl(this, 10, false)
        navigation = this.birdNavigation
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this, "Walk/Swim/Fly/Idle", 4
            ) { state: AnimationState<HybridBirdsParrotEntity> ->
                when {
                    state.isMoving && onGround() -> state.setAndContinue(DefaultAnimations.WALK)
                    !this.onGround() -> state.setAndContinue(DefaultAnimations.FLY)
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

        this.calculateFlapping()
    }

    companion object {

        @Suppress("UNUSED_PARAMETER")
        fun canBirdSpawn(
            type: EntityType<out HybridBirdsParrotEntity>,
            level: LevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            isBrightEnoughToSpawn(level, pos)
            return true
        }
    }
}