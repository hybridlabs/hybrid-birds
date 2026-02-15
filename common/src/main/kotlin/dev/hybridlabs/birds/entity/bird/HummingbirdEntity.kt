package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.bird.GuineaFowlEntity.Companion.BREEDING_INGREDIENT
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.BreedGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.level.Level

class HummingbirdEntity(entityType: EntityType<out HummingbirdEntity>, world: Level) :
    HBParrotEntity(entityType, world, false) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, TemptGoal(this, 1.0, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, BreedGoal(this, 1.1))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.FLYING_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }
    }
}