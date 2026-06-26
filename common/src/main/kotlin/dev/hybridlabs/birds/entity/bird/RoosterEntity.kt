package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.effect.HBStatusEffects
import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.sound.HBSoundEvents
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.ItemTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.animal.Chicken
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level

class RoosterEntity(entityType: EntityType<out RoosterEntity>, world: Level) :
    HBBirdEntity(entityType, world) {
    private var hasCalled: Boolean = false
    private var angerTicks = 0

    internal fun isAngry(): Boolean {
        return this.angerTicks > 0
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(3, TemptGoal(this, 1.0,Ingredient.of(ItemTags.VILLAGER_PLANTABLE_SEEDS), false))
        goalSelector.addGoal(6, LookAtPlayerGoal(this, RoosterEntity::class.java, 8.0f))
        goalSelector.addGoal(6, LookAtPlayerGoal(this, Chicken::class.java, 8.0f))
        goalSelector.addGoal(5, LookAtPlayerGoal(this, ChickEntity::class.java, 8.0f))
        goalSelector.addGoal(1, MeleeAttackGoal(this, 1.0, false))
        targetSelector.addGoal(1, HurtByTargetGoal(this))
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, RoosterEntity::class.java, true) { other ->
            other is RoosterEntity && this.isAngry() && other.isAngry()
        })
    }

    private fun isAngeringItem(stack: ItemStack): Boolean {
        return stack.`is`(Items.GLOW_BERRIES)
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val itemStack = player.getItemInHand(hand)

        if (isAngeringItem(itemStack) && !isAngry()) {
            if (!level().isClientSide) {
                if (!player.abilities.instabuild) {
                    itemStack.shrink(1)
                }
                playSound(SoundEvents.FOX_EAT, 1.0f, 1.0f)
                angerTicks = 200
            }
            return InteractionResult.SUCCESS
        }

        return super.mobInteract(player, hand)
    }

    override fun usePlayerItem(player: Player, hand: InteractionHand?, stack: ItemStack) {
        if (!player.abilities.instabuild) {
            stack.shrink(1)
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
        val timeOfDay = level().dayTime() % 24000L
        if (timeOfDay in 0..5 && !hasCalled) {
            this.playSound(HBSoundEvents.ROOSTER_CALL.get(), 1.0F, 1.0F)
            applySpeedEffectToNearbyPlayers()
            hasCalled = true
        } else if (timeOfDay !in 0..5) {
            hasCalled = false
        }
    }

    private fun applySpeedEffectToNearbyPlayers() {
        val effectRadius = 32.0
        val speedEffect = MobEffectInstance(HBStatusEffects.ROOSTERS_CALLING.get(), 6000, 0)

        val players = level().players()
        for (player in players) {
            if (this.distanceToSqr(player) <= effectRadius * effectRadius) {
                player.addEffect(speedEffect)
            }
        }
    }

    // region NBT

    override fun addAdditionalSaveData(compoundTag: CompoundTag) {
        super.addAdditionalSaveData(compoundTag)
        compoundTag.putInt("IsAngry", this.angerTicks)
    }

    override fun readAdditionalSaveData(compoundTag: CompoundTag) {
        super.readAdditionalSaveData(compoundTag)
        this.angerTicks = compoundTag.getInt("IsAngry")
    }

    // endregion

    // region SFX

    override fun getAmbientSound(): SoundEvent {
        return HBSoundEvents.ROOSTER_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HBSoundEvents.ROOSTER_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HBSoundEvents.ROOSTER_DIE.get()
    }

    // endregion

    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
        return HBEntityTypes.CHICK.get().create(serverLevel)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }
}