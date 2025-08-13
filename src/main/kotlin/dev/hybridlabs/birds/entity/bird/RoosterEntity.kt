package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.effect.HybridBirdsStatusEffects
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
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
import net.minecraft.entity.passive.ChickenEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.ItemTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World

class RoosterEntity(entityType: EntityType<out RoosterEntity>, world: World) :
    HybridBirdsBirdEntity(entityType, world) {
    private var roosterNavigation: EntityNavigation = createNavigation(world)
    private var hasCalled: Boolean = false
    private var angerTicks = 0

    internal fun isAngry(): Boolean {
        return this.angerTicks > 0
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    init {
        moveControl = MoveControl(this)
        navigation = roosterNavigation
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(2, EscapeDangerGoal(this, 0.6))
        goalSelector.add(3, TemptGoal(this, 0.5, Ingredient.fromTag(ItemTags.VILLAGER_PLANTABLE_SEEDS), false))
        goalSelector.add(5, WanderAroundGoal(this, 0.5))
        goalSelector.add(7, LookAroundGoal(this))
        goalSelector.add(6, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(6, LookAtEntityGoal(this, RoosterEntity::class.java, 8.0f))
        goalSelector.add(6, LookAtEntityGoal(this, ChickenEntity::class.java, 8.0f))
        goalSelector.add(5, LookAtEntityGoal(this, ChickEntity::class.java, 8.0f))
        goalSelector.add(1, MeleeAttackGoal(this, 0.5, false))
        targetSelector.add(1, RevengeGoal(this))
        targetSelector.add(2, ActiveTargetGoal(this, RoosterEntity::class.java, true) { other ->
            other is RoosterEntity && this.isAngry() && other.isAngry()
        })
    }

    private fun isAngeringItem(stack: ItemStack): Boolean {
        return stack.isOf(Items.GLOW_BERRIES)
    }

    override fun interactMob(player: PlayerEntity, hand: Hand?): ActionResult {
        val itemStack = player.getStackInHand(hand)

        if (isAngeringItem(itemStack) && !isAngry()) {
            if (!world.isClient) {
                if (!player.abilities.creativeMode) {
                    itemStack.decrement(1)
                }
                angerTicks = 200
            }
            return ActionResult.SUCCESS
        }

        return super.interactMob(player, hand)
    }

    override fun eat(player: PlayerEntity, hand: Hand?, stack: ItemStack) {
        if (!player.abilities.creativeMode) {
            stack.decrement(1)
        }

        this.angerTicks = 200
    }

    override fun tick() {
        super.tick()
        if (angerTicks > 0) {
            angerTicks--
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

    // region NBT

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("IsAngry", this.angerTicks)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.angerTicks = nbt.getInt("IsAngry")
    }

    // endregion

    // region SFX

    override fun getAmbientSound(): SoundEvent {
        return HybridBirdsSoundEvents.ROOSTER_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.ROOSTER_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.ROOSTER_DIE
    }

    // endregion

    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? {
        return HybridBirdsEntityTypes.CHICK.create(world)
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