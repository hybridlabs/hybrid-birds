package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.item.HybridBirdsItems
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
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent

class GuineaFowlEntity(entityType: EntityType<out GuineaFowlEntity>, world: Level) :
    HybridBirdsBirdEntity(entityType, world, false) {
    private var guineaFowlNavigation: PathNavigation = createNavigation(world)
    private var eggLayTime: Int = 0

    init {
        moveControl = MoveControl(this)
        navigation = guineaFowlNavigation
        this.eggLayTime = random.nextInt(6000) + 6000
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, TemptGoal(this, 1.0, GooseEntity.Companion.BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, BreedGoal(this, 1.1))
    }

    override fun aiStep() {
        super.aiStep()
        if ((!level().isClientSide && this.isAlive && !this.isBaby && --this.eggLayTime <= 0)) {
            this.playSound(
                SoundEvents.CHICKEN_EGG,
                1.0f,
                (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
            )
            this.spawnAtLocation(HybridBirdsItems.GUINEA_FOWL_EGG.get())
            this.gameEvent(GameEvent.ENTITY_PLACE)
            this.eggLayTime = random.nextInt(6000) + 6000
        }
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.CHICKEN_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.CHICKEN_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.CHICKEN_AMBIENT
    }

    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
        return HybridBirdsEntityTypes.GUINEA_FOWL.get().create(serverLevel)
    }

    override fun spawnChildFromBreeding(world: ServerLevel, other: Animal) {
        val babyCount = 1 + random.nextInt(4)

        repeat(babyCount) {
            val passiveEntity = this.getBreedOffspring(world, other)
            if (passiveEntity != null) {
                passiveEntity.isBaby = true
                passiveEntity.moveTo(this.x, this.y, this.z, 0.0f, 0.0f)
                this.finalizeSpawnChildFromBreeding(world, other, passiveEntity)
                world.addFreshEntityWithPassengers(passiveEntity)
            }
        }
    }

    override fun isFood(stack: ItemStack): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(ItemTags.VILLAGER_PLANTABLE_SEEDS)
    }
}