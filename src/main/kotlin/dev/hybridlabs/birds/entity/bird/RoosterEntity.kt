package dev.hybridlabs.birds.entity.bird

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.EscapeDangerGoal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.ai.goal.TemptGoal
import net.minecraft.entity.ai.goal.WanderAroundGoal
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.ItemTags
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class RoosterEntity(entityType: EntityType<out RoosterEntity>, world: World) :
    BirdEntity(entityType, world) {
    private var roosterNavigation: EntityNavigation = createNavigation(world)
    private var hasCalled: Boolean = false

    init {
        moveControl = MoveControl(this)
        navigation = roosterNavigation
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(0, EscapeDangerGoal(this, 0.6))
        goalSelector.add(1, TemptGoal(this, 0.6, Ingredient.fromTag(ItemTags.VILLAGER_PLANTABLE_SEEDS), false))
        goalSelector.add(2, WanderAroundGoal(this, 0.5))
        goalSelector.add(2, LookAroundGoal(this))
        goalSelector.add(11, LookAtEntityGoal(this, PlayerEntity::class.java, 10.0f))
    }

    override fun tick() {
        super.tick()
        morningCall()
    }

    private fun morningCall() {
        val timeOfDay = world.timeOfDay % 24000L
        if (timeOfDay in 0..5 && !hasCalled) {
            this.playSound(SoundEvents.ENTITY_CHICKEN_DEATH, 2.0F, 1.0F)
            applySpeedEffectToNearbyPlayers()
            hasCalled = true
        } else if (timeOfDay !in 0..5) {
            hasCalled = false
        }
    }

    private fun applySpeedEffectToNearbyPlayers() {
        val effectRadius = 32.0
        val speedEffect = StatusEffectInstance(StatusEffects.SPEED, 6000, 0)

        val players = world.players
        for (player in players) {
            if (this.squaredDistanceTo(player) <= effectRadius * effectRadius) {
                player.addStatusEffect(speedEffect)
            }
        }
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_CHICKEN_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_CHICKEN_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_CHICKEN_AMBIENT
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}
