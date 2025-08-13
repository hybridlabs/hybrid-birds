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
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.ItemTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent

class TurkeyEntity(entityType: EntityType<out TurkeyEntity>, world: World) :
    HybridBirdsBirdEntity(entityType, world) {
    private var turkeyNavigation: EntityNavigation = createNavigation(world)
    private var eggLayTime: Int = 0

    init {
        moveControl = MoveControl(this)
        navigation = turkeyNavigation
        this.eggLayTime = random.nextInt(6000) + 6000
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(0, EscapeDangerGoal(this, 0.6))
        goalSelector.add(1, TemptGoal(this, 0.6, Ingredient.fromTag(ItemTags.VILLAGER_PLANTABLE_SEEDS), false))
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
            this.dropItem(HybridBirdsItems.TURKEY_EGG)
            this.emitGameEvent(GameEvent.ENTITY_PLACE)
            this.eggLayTime = random.nextInt(6000) + 6000
        }
    }

    private fun isFatteningItem(stack: ItemStack): Boolean {
        return stack.isOf(Items.WHEAT)
    }

    fun getStuffingLevel(): Int {
        return dataTracker.get(STUFFING_LEVEL)
    }

    private fun setStuffingLevel(level: Int) {
        val newSpeed = level.coerceIn(0, 2)
        dataTracker.set(STUFFING_LEVEL, newSpeed)

        val speed = when (newSpeed) {
            0 -> 0.5
            1 -> 0.45
            2 -> 0.4
            else -> 0.5
        }

        getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = speed
    }

    override fun interactMob(player: PlayerEntity, hand: Hand?): ActionResult {
        val itemStack = player.getStackInHand(hand)

        if (isFatteningItem(itemStack)) {
            if (!world.isClient) {
                val currentFatness = getStuffingLevel()
                if (currentFatness < 2) {
                    setStuffingLevel(currentFatness + 1)
                    playSound(SoundEvents.ENTITY_FOX_EAT, 1.0f, 1.0f)
                    if (!player.abilities.creativeMode) {
                        itemStack.decrement(1)
                    }
                }
            }
            return ActionResult.SUCCESS
        }
        return super.interactMob(player, hand)
    }


    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(STUFFING_LEVEL, 0)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("StuffingLevel", getStuffingLevel())
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        setStuffingLevel(nbt.getInt("FatnessLevel"))
    }

    // region SFX

    override fun getAmbientSound(): SoundEvent {
        return HybridBirdsSoundEvents.TURKEY_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.TURKEY_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.TURKEY_DIE
    }

    // endregion

    override fun isBreedingItem(stack: ItemStack?): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? {
        return HybridBirdsEntityTypes.POULT.create(world)
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }

        private val STUFFING_LEVEL: TrackedData<Int> =
            DataTracker.registerData(TurkeyEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        val BREEDING_INGREDIENT: Ingredient = Ingredient.fromTag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
    }
}