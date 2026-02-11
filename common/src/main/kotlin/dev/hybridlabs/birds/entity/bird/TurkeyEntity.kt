package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.loot.HybridBirdsLootTables
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceLocation
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
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent

class TurkeyEntity(entityType: EntityType<out TurkeyEntity>, world: Level) :
    HybridBirdsBirdEntity(entityType, world, false) {
    private var turkeyNavigation: PathNavigation = createNavigation(world)
    private var eggLayTime: Int = 0

    init {
        moveControl = MoveControl(this)
        navigation = turkeyNavigation
        this.eggLayTime = random.nextInt(6000) + 6000
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun getDefaultLootTable(): ResourceLocation {
        return when (getStuffingLevel()) {
            1 -> HybridBirdsLootTables.TURKEY_FAT
            2 -> HybridBirdsLootTables.TURKEY_STUFFED
            else ->
                return super.defaultLootTable
        }
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(0, PanicGoal(this, 0.6))
        goalSelector.addGoal(1, TemptGoal(this, 0.6, Ingredient.of(ItemTags.VILLAGER_PLANTABLE_SEEDS), false))
        goalSelector.addGoal(2, BreedGoal(this, 0.5))
        goalSelector.addGoal(2, RandomStrollGoal(this, 0.5))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(5, FollowParentGoal(this, 1.1))
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
            this.spawnAtLocation(HybridBirdsItems.TURKEY_EGG.get())
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

        if (isFatteningItem(itemStack)) {
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


    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(STUFFING_LEVEL, 0)
    }

    override fun addAdditionalSaveData(compoundTag: CompoundTag) {
        super.addAdditionalSaveData(compoundTag)
        compoundTag.putInt("StuffingLevel", getStuffingLevel())
    }

    override fun readAdditionalSaveData(compoundTag: CompoundTag) {
        super.readAdditionalSaveData(compoundTag)
        setStuffingLevel(compoundTag.getInt("FatnessLevel"))
    }

    // region SFX

    override fun getAmbientSound(): SoundEvent {
        return HybridBirdsSoundEvents.TURKEY_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.TURKEY_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.TURKEY_DIE.get()
    }

    // endregion

    override fun isFood(stack: ItemStack): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
        return HybridBirdsEntityTypes.TURKEY.get().create(serverLevel)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        private val STUFFING_LEVEL: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(TurkeyEntity::class.java, EntityDataSerializers.INT)

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(ItemTags.VILLAGER_PLANTABLE_SEEDS)
    }
}