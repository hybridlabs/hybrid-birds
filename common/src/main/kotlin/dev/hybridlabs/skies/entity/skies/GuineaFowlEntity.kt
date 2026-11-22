package dev.hybridlabs.skies.entity.skies

import dev.hybridlabs.hapi.entity.ai.goal.skies.FlyingAnimalBreedGoal
import dev.hybridlabs.hapi.entity.base.skies.BaseFlyingAnimal
import dev.hybridlabs.skies.entity.HSEntityTypes
import dev.hybridlabs.skies.util.ingredientOf
import dev.hybridlabs.skies.item.HSItems
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.animal.chicken.ChickenSoundVariants
import net.minecraft.tags.ItemTags
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent

class GuineaFowlEntity(entityType: EntityType<out GuineaFowlEntity>, world: Level) :
    BaseFlyingAnimal(entityType, world) {
    private var eggLayTime: Int = 0

    init {
        this.eggLayTime = random.nextInt(6000) + 6000
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, FlyingAnimalBreedGoal(this, 1.1))
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
            this.spawnAtLocation(level() as ServerLevel, HSItems.GUINEA_FOWL_EGG.get())
            this.gameEvent(GameEvent.ENTITY_PLACE)
            this.eggLayTime = random.nextInt(6000) + 6000
        }
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return chickenSounds().hurtSound().value()
    }

    override fun getDeathSound(): SoundEvent {
        return chickenSounds().deathSound().value()
    }

    override fun getAmbientSound(): SoundEvent {
        return chickenSounds().ambientSound().value()
    }

    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
        return HSEntityTypes.GUINEA_FOWL.get().create(serverLevel, EntitySpawnReason.BREEDING)
    }

    override fun spawnChildFromBreeding(level: ServerLevel, mate: Animal) {
        val babyCount = 1 + random.nextInt(4)

        repeat(babyCount) {
            val passiveEntity = this.getBreedOffspring(level, mate)
            if (passiveEntity != null) {
                passiveEntity.isBaby = true
                passiveEntity.snapTo(this.x, this.y, this.z, 0.0f, 0.0f)
                this.finalizeSpawnChildFromBreeding(level, mate, passiveEntity)
                level.addFreshEntityWithPassengers(passiveEntity)
            }
        }
    }

    override fun isFood(stack: ItemStack): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    private fun chickenSounds() =
        SoundEvents.CHICKEN_SOUNDS[ChickenSoundVariants.SoundSet.CLASSIC]!!.adultSounds()

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.FLYING_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        val BREEDING_INGREDIENT: Ingredient get() = ingredientOf(ItemTags.VILLAGER_PLANTABLE_SEEDS)
    }
}
