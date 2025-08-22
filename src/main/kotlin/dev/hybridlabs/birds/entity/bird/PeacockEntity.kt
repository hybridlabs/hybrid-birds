package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.ItemTags
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation

class PeacockEntity(entityType: EntityType<out PeacockEntity>, world: Level) :
    HybridBirdsBirdEntity(entityType, world, false) {
    private var peacockNavigation: PathNavigation = createNavigation(world)
    private var eggLayTime: Int = 0
    private var tailUp = false
    private var tailUpTimer = 0

    init {
        moveControl = MoveControl(this)
        navigation = peacockNavigation
        this.eggLayTime = random.nextInt(6000) + 6000
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(0, PanicGoal(this, 0.6))
        goalSelector.addGoal(1, TemptGoal(this, 0.6, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, BreedGoal(this, 0.5))
        goalSelector.addGoal(2, RandomStrollGoal(this, 0.5))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(11, LookAtPlayerGoal(this, Player::class.java, 10.0f))
    }

    override fun aiStep() {
        super.aiStep()
        if ((!level().isClientSide && this.isAlive && !this.isBaby && --this.eggLayTime <= 0)) {
            this.playSound(
                SoundEvents.CHICKEN_EGG,
                1.0f,
                (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
            )
            this.spawnAtLocation(HybridBirdsItems.PEACOCK_EGG)
            this.gameEvent(GameEvent.ENTITY_PLACE)
            this.eggLayTime = random.nextInt(6000) + 6000
        }
    }

    override fun tick() {
        super.tick()

        if (tailUpTimer > 0) {
            tailUpTimer--
        } else {
            tailUp = !tailUp

            val minTicks = 1200
            val maxTicks = 3600
            tailUpTimer = (minTicks..maxTicks).random()
        }
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this, "Walk/Fly/Idle", 4
            ) { state: AnimationState<HybridBirdsBirdEntity> ->
                when {
                    state.isMoving && onGround() -> state.setAndContinue(DefaultAnimations.WALK)
                    !this.onGround() && !isInWater -> state.setAndContinue(DefaultAnimations.FLY)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
        controllerRegistrar.add(AnimationController(this, "Tail", 20) { state ->
            val animation = when {
                tailUp -> TAIL_UP
                else -> TAIL_DOWN
            }
            state.setAndContinue(animation)
        })
    }

    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
        return HybridBirdsEntityTypes.PEACHICK.create(serverLevel)
    }

    override fun getAmbientSound(): SoundEvent {
        return HybridBirdsSoundEvents.PEACOCK_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.PEACOCK_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.PEACOCK_DIE
    }

    override fun isFood(stack: ItemStack?): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        val TAIL_DOWN: RawAnimation = RawAnimation.begin().thenPlay("misc.tail_down")
        val TAIL_UP: RawAnimation = RawAnimation.begin().thenPlay("misc.tail_up")

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(ItemTags.VILLAGER_PLANTABLE_SEEDS)
    }
}