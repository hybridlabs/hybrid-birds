package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.entity.ai.BirdFloatControl
import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent

class GooseEntity(entityType: EntityType<out GooseEntity>, world: Level) :
    HybridBirdsBirdEntity(entityType, world, true) {
    private var gooseNavigation = AmphibiousPathNavigation(this, world)
    private var eggLayTime: Int = 0

    init {
        moveControl = BirdFloatControl(this)
        navigation = gooseNavigation
        this.eggLayTime = random.nextInt(6000) + 6000
    }

    override fun getWaterline(): Float {
        return 0.3f
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, PanicGoal(this, 0.6))
        goalSelector.addGoal(1, TemptGoal(this, 0.5, BREEDING_INGREDIENT, false))
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
            this.spawnAtLocation(HybridBirdsItems.GOOSE_EGG.get())
            this.gameEvent(GameEvent.ENTITY_PLACE)
            this.eggLayTime = random.nextInt(6000) + 6000
        }
    }

    override fun getAmbientSound(): SoundEvent {
        return HybridBirdsSoundEvents.GOOSE_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.GOOSE_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.GOOSE_DIE.get()
    }

    override fun isFood(stack: ItemStack): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
        return HybridBirdsEntityTypes.GOSLING!!.get().create(serverLevel)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(
            Items.WHEAT,
            Items.COD
        )
    }
}