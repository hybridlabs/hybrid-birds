package dev.hybridlabs.birds.entity.bird

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.ItemTags
import net.minecraft.world.World

class HummingbirdEntity(entityType: EntityType<out HummingbirdEntity>, world: World) :
    HybridBirdsBirdEntity(entityType, world) {

    override fun initGoals() {
        goalSelector.add(0, EscapeDangerGoal(this, 0.75))
        goalSelector.add(2, FlyGoal(this, 1.0))
        goalSelector.add(1, WanderAroundGoal(this, 0.6))
        goalSelector.add(6, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
        goalSelector.add(7, LookAroundGoal(this))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.75)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}