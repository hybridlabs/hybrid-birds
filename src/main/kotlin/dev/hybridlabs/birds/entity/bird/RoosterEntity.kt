package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.effect.HybridBirdsStatusEffects
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.ItemTags
import net.minecraft.sound.SoundEvent
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World
import java.util.*

class RoosterEntity(entityType: EntityType<out RoosterEntity>, world: World) :
    HybridBirdsBirdEntity(entityType, world) {
    private var roosterNavigation: EntityNavigation = createNavigation(world)
    private var hasCalled: Boolean = false
    private var angerTicks = 0


    private fun isAngry(): Boolean {
        return this.angerTicks > 0
    }

    private fun isAngeringItem(stack: ItemStack): Boolean {
        return stack.isOf(Items.GLOW_BERRIES)
    }

    override fun interactMob(player: PlayerEntity, hand: Hand?): ActionResult {
        val itemStack = player.getStackInHand(hand)
        if (this.isAngeringItem(itemStack)) {
            val i = this.getBreedingAge()
            if (!world.isClient && i == 0 && this.canEat()) {
                this.eat(player, hand, itemStack)
                return ActionResult.SUCCESS
            }

            if (world.isClient) {
                return ActionResult.CONSUME
            }
        }

        return super.interactMob(player, hand)
    }

    override fun eat(player: PlayerEntity, hand: Hand?, stack: ItemStack) {
        if (!player.abilities.creativeMode) {
            stack.decrement(1)
        }

        this.angerTicks = 200
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("IsAngry", this.angerTicks)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.angerTicks = nbt.getInt("IsAngry")
    }

    init {
        moveControl = MoveControl(this)
        navigation = roosterNavigation
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(2, EscapeDangerGoal(this, 0.6))
        goalSelector.add(3, TemptGoal(this, 0.6, Ingredient.fromTag(ItemTags.VILLAGER_PLANTABLE_SEEDS), false))
        goalSelector.add(4, WanderAroundGoal(this, 0.5))
        goalSelector.add(4, LookAroundGoal(this))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 10.0f))
        goalSelector.add(0, MeleeAttackGoal(this, 1.0, true))
        goalSelector.add(0, LookAtOpponentGoal())
        goalSelector.add(0, RoosterRevengeGoal(this))
        goalSelector.add(1, PounceAtTargetGoal(this, 0.4F))
        targetSelector.add(0, ActiveTargetGoal(this, RoosterEntity::class.java, true) { other -> other is RoosterEntity && this.isAngry() && other.isAngry() })
    }

    override fun tick() {
        super.tick()
        if (!isAngry()) {
            val nearby = world.getEntitiesByClass(RoosterEntity::class.java, boundingBox.expand(4.0)) { it != this }
            if (nearby.any { it.isAngry() }) {
                this.angerTicks = 200
            }
        }
        morningCall()
    }

    private fun morningCall() {
        val timeOfDay = world.timeOfDay % 24000L
        if (timeOfDay in 0..5 && !hasCalled) {
            this.playSound(HybridBirdsSoundEvents.ROOSTER_CALL, 1.0F, 1.0F)
            applySpeedEffectToNearbyPlayers()
            hasCalled = true
        } else if (timeOfDay !in 0..5) {
            hasCalled = false
        }
    }

    private fun applySpeedEffectToNearbyPlayers() {
        val effectRadius = 32.0
        val speedEffect = StatusEffectInstance(HybridBirdsStatusEffects.ROOSTERS_CALLING, 6000, 0)

        val players = world.players
        for (player in players) {
            if (this.squaredDistanceTo(player) <= effectRadius * effectRadius) {
                player.addStatusEffect(speedEffect)
            }
        }
    }

    override fun getAmbientSound(): SoundEvent {
        return HybridBirdsSoundEvents.ROOSTER_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.ROOSTER_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.ROOSTER_DIE
    }

    class RoosterRevengeGoal(rooster: RoosterEntity) : RevengeGoal(rooster) {
        override fun shouldContinue(): Boolean {
            val target = mob.target
            return target is RoosterEntity && super.shouldContinue()
        }

        override fun canStart(): Boolean {
            val target = mob.attacker
            return target is RoosterEntity && super.canStart()
        }
    }

    private inner class LookAtOpponentGoal : Goal() {
        init {
            controls = EnumSet.of(Control.LOOK)
        }

        override fun canStart(): Boolean {
            val target = this@RoosterEntity.target
            return target is RoosterEntity && this@RoosterEntity.isAngry()
        }

        override fun shouldContinue(): Boolean {
            val target = this@RoosterEntity.target
            return target is RoosterEntity && target.isAlive && this@RoosterEntity.isAngry()
        }

        override fun tick() {
            val target = this@RoosterEntity.target
            if (target != null) {
                this@RoosterEntity.lookControl.lookAt(
                    target.x,
                    target.eyeY,
                    target.z,
                    180.0f,
                    20.0f
                )
            }
        }
    }


    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }
    }
}