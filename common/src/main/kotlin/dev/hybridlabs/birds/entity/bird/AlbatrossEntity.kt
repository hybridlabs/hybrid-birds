package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.entity.ai.control.BirdFlyFloatControl
import dev.hybridlabs.birds.sound.HBSoundEvents
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.tags.ItemTags
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.animal.Turtle
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.BlockPathTypes

class AlbatrossEntity(type: EntityType<out AlbatrossEntity>, world: Level) :
    HBAquaticBirdEntity(type, world) {

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = BirdFlyFloatControl(this, 10, false)
        navigation = FlyingPathNavigation(this, level)
        lookControl = LookControl(this)

        return FlyingPathNavigation(this, level)
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, PanicGoal(this, 1.1))
        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(1, TemptGoal(this, 1.0, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, WaterAvoidingRandomFlyingGoal(this, 1.0))
        goalSelector.addGoal(2, LookAtPlayerGoal(this, Player::class.java, 8.0f))
        goalSelector.addGoal(5, MeleeAttackGoal(this, 1.0, true))
        targetSelector.addGoal(6, NearestAttackableTargetGoal(this, Turtle::class.java, false, Turtle.BABY_ON_LAND_SELECTOR)
        )
    }

    override fun getWaterline(): Float {
        return if (this.isBaby)
            0.125f
        else
            return 0.2f
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 3
    }

    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AlbatrossEntity? {
        return HBEntityTypes.ALBATROSS.get().create(serverLevel)
    }

    override fun getAmbientSound(): SoundEvent {
        return HBSoundEvents.ALBATROSS_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HBSoundEvents.ALBATROSS_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HBSoundEvents.ALBATROSS_DIE.get()
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.FLYING_SPEED, 0.8)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(
            ItemTags.FISHES
        )
    }
}