package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.entity.ai.BirdFlyFloatControl
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
import net.minecraft.world.level.Level

class SeagullEntity(type: EntityType<out SeagullEntity>, world: Level) :
    HBParrotEntity(type, world, false) {

    init {
        moveControl = BirdFlyFloatControl(this, 10, false)
        navigation = FlyingPathNavigation(this, world)
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 3
    }

    override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): SeagullEntity? {
        return HybridBirdsEntityTypes.SEAGULL.get().create(serverLevel)
    }

    override fun getAmbientSound(): SoundEvent {
        return HybridBirdsSoundEvents.DUCK_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.DUCK_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.DUCK_DIE.get()
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.FLYING_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }
    }
}