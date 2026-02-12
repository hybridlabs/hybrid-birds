package dev.hybridlabs.birds.entity.bird

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
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
open class HybridBirdsRatiteEntity(
    type: EntityType<out HybridBirdsRatiteEntity>,
    world: Level,
) :
    Animal(type, world), PlayerRideableJumping,
    GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var birdNavigation = GroundPathNavigation(this, world)

    init {
        moveControl = MoveControl(this)
        navigation = this.birdNavigation
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, PanicGoal(this, 1.3))
        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(1, BreedGoal(this, 1.0))
        goalSelector.addGoal(2, TemptGoal(this, 1.0, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(0, WaterAvoidingRandomStrollGoal(this,1.0))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(5, FollowParentGoal(this, 1.1))
        goalSelector.addGoal(11, LookAtPlayerGoal(this, Player::class.java, 8.0f))
    }

    override fun isFood(stack: ItemStack): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    @Nullable
    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
        return null
    }

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this, "Walk/Fly/Idle", 4
            ) { state: AnimationState<HybridBirdsRatiteEntity> ->
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
    //#endregion

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 1.1f
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return false
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    override fun checkFallDamage(heightDifference: Double, onGround: Boolean, state: BlockState, landedPosition: BlockPos) {}

    override fun aiStep() {
        super.aiStep()
        val vec3d = this.deltaMovement
        if (!this.onGround() && vec3d.y < 0.0) {
            this.deltaMovement = vec3d.multiply(1.0, 0.75, 1.0)
        }
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

    //#region Jumping
    override fun canJump(): Boolean {
        return true
    }

    override fun onPlayerJump(power: Int) {
        if (!this.onGround()) return

        val jumpStrength = power / 100.0f

        val verticalBoost = 0.4 + (jumpStrength * 0.6)

        this.deltaMovement = this.deltaMovement.add(0.0, verticalBoost, 0.0)
        this.hasImpulse = true
    }

    override fun handleStartJump(power: Int) {
        if (canJump()) {
            this.playSound(SoundEvents.CAMEL_DASH, 1.0f, 1.0f)
            this.onPlayerJump(power)
        }
    }

    override fun handleStopJump() {
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val stack = player.getItemInHand(hand)

        if (isFood(stack)) {
            return super.mobInteract(player, hand)
        }

        if (!isBaby && !isVehicle && !player.isSecondaryUseActive) {
            if (!level().isClientSide) {
                player.startRiding(this)
            }
            return InteractionResult.sidedSuccess(level().isClientSide)
        }

        return super.mobInteract(player, hand)
    }

    override fun getControllingPassenger(): LivingEntity? {
        return if (firstPassenger is Player) firstPassenger as Player else null
    }


    override fun tickRidden(player: Player, travelVector: Vec3) {
        super.tickRidden(player, travelVector)
        this.setRot(player.yRot, player.xRot * 0.5f)
        this.yHeadRot = this.yRot
        this.yBodyRot = this.yHeadRot
        this.yRotO = this.yBodyRot
    }

    override fun travel(travelVector: Vec3) {
        if (isAlive && isVehicle) {
            val passenger = controllingPassenger
            if (passenger is Player) {

                yRot = passenger.yRot
                xRot = passenger.xRot * 0.5f
                yRotO = yRot
                yHeadRot = yRot

                val forward = passenger.zza
                val strafe = passenger.xxa

                val speed = getAttributeValue(Attributes.MOVEMENT_SPEED).toFloat()

                setSpeed(speed)
                super.travel(Vec3(strafe.toDouble(), travelVector.y, forward.toDouble()))
                return
            }
        }

        super.travel(travelVector)
    }

    override fun getRiddenInput(player: Player, travelVector: Vec3): Vec3 {
        return Vec3(0.0, 0.0, 1.0)
    }

    override fun getRiddenSpeed(player: Player): Float {
        return getAttributeValue(Attributes.MOVEMENT_SPEED).toFloat()
    }
    //#endregion

    companion object {
        @Suppress("UNUSED_PARAMETER")
        fun canBirdSpawn(
            type: EntityType<out HybridBirdsRatiteEntity>,
            level: LevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return isBrightEnoughToSpawn(level, pos) &&
                    level.getBlockState(pos.below()).`is`(BlockTags.ANIMALS_SPAWNABLE_ON)
        }

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(ItemTags.VILLAGER_PLANTABLE_SEEDS)
    }
}