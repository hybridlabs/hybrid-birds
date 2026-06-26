package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.entity.ai.goal.BirdBreedGoal
import dev.hybridlabs.birds.item.HBItems
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.ItemTags
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent

class GuineaFowlEntity(entityType: EntityType<out GuineaFowlEntity>, world: Level) :
    HBBirdEntity(entityType, world) {
    private var eggLayTime: Int = 0

    init {
        this.eggLayTime = random.nextInt(6000) + 6000
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, BirdBreedGoal(this, 1.1))
        goalSelector.addGoal(2, TemptGoal(this, 1.0, BREEDING_INGREDIENT, false))
    }

    override fun aiStep() {
        super.aiStep()
        if ((!level().isClientSide && this.isAlive && !this.isBaby && --this.eggLayTime <= 0)) {
            this.playSound(
                SoundEvents.CHICKEN_EGG,
                1.0f,
                (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f
            )
            this.spawnAtLocation(HBItems.GUINEA_FOWL_EGG.get())
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
        return HBEntityTypes.GUINEA_FOWL.get().create(serverLevel)
    }

    override fun spawnChildFromBreeding(level: ServerLevel, mate: HBBirdEntity) {
        val babyCount = 1 + random.nextInt(4)

        repeat(babyCount) {
            val passiveEntity = this.getBreedOffspring(level, mate)
            if (passiveEntity != null) {
                passiveEntity.isBaby = true
                passiveEntity.moveTo(this.x, this.y, this.z, 0.0f, 0.0f)
                this.finalizeSpawnChildFromBreeding(level, mate)
                level.addFreshEntityWithPassengers(passiveEntity)
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
                .add(Attributes.FLYING_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(ItemTags.VILLAGER_PLANTABLE_SEEDS)
    }
}