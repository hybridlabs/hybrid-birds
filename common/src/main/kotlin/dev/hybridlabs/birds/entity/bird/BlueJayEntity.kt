package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level


class BlueJayEntity(type: EntityType<out BlueJayEntity>, world: Level) :
    HybridBirdsParrotEntity(type, world) {

    override fun getMaxSpawnClusterSize(): Int {
        return 2
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
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.FLYING_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }
    }
}