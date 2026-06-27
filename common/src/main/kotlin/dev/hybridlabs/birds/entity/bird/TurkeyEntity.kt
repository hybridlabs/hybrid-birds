package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.entity.ai.goal.BirdBreedGoal
import dev.hybridlabs.birds.item.HBItems
import dev.hybridlabs.birds.loot.HBLootTables
import dev.hybridlabs.birds.sound.HBSoundEvents
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.registries.Registries
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.ItemTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.storage.loot.LootTable

class TurkeyEntity(entityType: EntityType<out TurkeyEntity>, world: Level) :
    HBBirdEntity(entityType, world) {
    private var eggLayTime: Int = 0

    init {
        this.eggLayTime = random.nextInt(6000) + 6000
    }

    override fun getDefaultLootTable(): ResourceKey<LootTable?> {
        return when (getStuffingLevel()) {
            1 -> ResourceKey.create(
                Registries.LOOT_TABLE, HBLootTables.TURKEY_FAT)
            2 -> ResourceKey.create(
                Registries.LOOT_TABLE, HBLootTables.TURKEY_STUFFED)
            else ->
                return super.defaultLootTable
        }
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, BirdBreedGoal(this, 1.1))
        goalSelector.addGoal(2, TemptGoal(this, 1.0, BREEDING_INGREDIENT, false))
    }

    override fun aiStep() {
        super.aiStep()
        if (this.getAge() != 0) {
            this.inLove = 0
        }

        if (this.inLove > 0) {
            --this.inLove
            if (this.inLove % 10 == 0) {
                val d0 = this.random.nextGaussian() * 0.02
                val d1 = this.random.nextGaussian() * 0.02
                val d2 = this.random.nextGaussian() * 0.02
                this.level().addParticle(
                    ParticleTypes.HEART,
                    this.getRandomX(1.0),
                    this.randomY + 0.5,
                    this.getRandomZ(1.0),
                    d0,
                    d1,
                    d2
                )
            }
        }

        if ((!level().isClientSide && this.isAlive && !this.isBaby && --this.eggLayTime <= 0)) {
            this.playSound(
                SoundEvents.CHICKEN_EGG,
                1.0f,
                (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
            )
            this.spawnAtLocation(HBItems.TURKEY_EGG.get())
            this.gameEvent(GameEvent.ENTITY_PLACE)
            this.eggLayTime = random.nextInt(6000) + 6000
        }
    }

    private fun isFatteningItem(stack: ItemStack): Boolean {
        return stack.`is`(Items.WHEAT)
    }

    fun getStuffingLevel(): Int {
        return entityData.get(STUFFING_LEVEL)
    }

    private fun setStuffingLevel(level: Int) {
        val newSpeed = level.coerceIn(0, 2)
        entityData.set(STUFFING_LEVEL, newSpeed)

        val speed = when (newSpeed) {
            0 -> 0.5
            1 -> 0.45
            2 -> 0.4
            else -> 0.5
        }

        getAttribute(Attributes.MOVEMENT_SPEED)?.baseValue = speed
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val itemStack = player.getItemInHand(hand)

        if (isFatteningItem(itemStack) && !this.isBaby) {
            if (!level().isClientSide) {
                val currentFatness = getStuffingLevel()
                if (currentFatness < 2) {
                    setStuffingLevel(currentFatness + 1)
                    playSound(SoundEvents.FOX_EAT, 1.0f, 1.0f)
                    if (!player.abilities.instabuild) {
                        itemStack.shrink(1)
                    }
                }
            }
            return InteractionResult.SUCCESS
        }
        return super.mobInteract (player, hand)
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(STUFFING_LEVEL, 0)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt("StuffingLevel", getStuffingLevel())
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        setStuffingLevel(compound.getInt("FatnessLevel"))
    }

    // region SFX

    override fun getAmbientSound(): SoundEvent {
        return HBSoundEvents.TURKEY_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HBSoundEvents.TURKEY_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HBSoundEvents.TURKEY_DIE.get()
    }

    // endregion

    override fun isFood(stack: ItemStack): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
        return HBEntityTypes.TURKEY.get().create(serverLevel)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.FLYING_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        private val STUFFING_LEVEL: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(TurkeyEntity::class.java, EntityDataSerializers.INT)

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(ItemTags.VILLAGER_PLANTABLE_SEEDS)
    }
}