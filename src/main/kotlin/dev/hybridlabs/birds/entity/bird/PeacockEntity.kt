package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.ItemTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation

class PeacockEntity(entityType: EntityType<out PeacockEntity>, world: World) :
    HybridBirdsBirdEntity(entityType, world) {
    private var peacockNavigation: EntityNavigation = createNavigation(world)
    private var eggLayTime: Int = 0
    private var tailUp = false
    private var tailUpTimer = 0

    init {
        moveControl = MoveControl(this)
        navigation = peacockNavigation
        this.eggLayTime = random.nextInt(6000) + 6000
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(0, EscapeDangerGoal(this, 0.6))
        goalSelector.add(1, TemptGoal(this, 0.6, BREEDING_INGREDIENT, false))
        goalSelector.add(2, AnimalMateGoal(this, 0.5))
        goalSelector.add(2, WanderAroundGoal(this, 0.5))
        goalSelector.add(2, LookAroundGoal(this))
        goalSelector.add(11, LookAtEntityGoal(this, PlayerEntity::class.java, 10.0f))
    }

    override fun tickMovement() {
        super.tickMovement()
        if ((!world.isClient && this.isAlive && !this.isBaby && --this.eggLayTime <= 0)) {
            this.playSound(
                SoundEvents.ENTITY_CHICKEN_EGG,
                1.0f,
                (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
            )
            this.dropItem(HybridBirdsItems.PEACOCK_EGG)
            this.emitGameEvent(GameEvent.ENTITY_PLACE)
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
                    state.isMoving && isOnGround -> state.setAndContinue(DefaultAnimations.WALK)
                    !this.isOnGround && !isTouchingWater -> state.setAndContinue(DefaultAnimations.FLY)
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

    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? {
        return HybridBirdsEntityTypes.PEACHICK.create(world)
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

    override fun isBreedingItem(stack: ItemStack?): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }

        val TAIL_DOWN: RawAnimation = RawAnimation.begin().thenPlay("misc.tail_down")
        val TAIL_UP: RawAnimation = RawAnimation.begin().thenPlay("misc.tail_up")

        val BREEDING_INGREDIENT: Ingredient = Ingredient.fromTag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
    }
}