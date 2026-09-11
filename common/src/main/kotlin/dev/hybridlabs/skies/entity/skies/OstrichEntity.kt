package dev.hybridlabs.skies.entity.skies

import dev.hybridlabs.skies.sound.HSSoundEvents
import dev.hybridlabs.hapi.entity.base.flying.BaseRatiteEntity
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class OstrichEntity(entityType: EntityType<out OstrichEntity>, world: Level) :
    BaseRatiteEntity(entityType, world, true) {

    //override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): AgeableMob? {
    //    return HSEntityTypes.OSTRICH.get().create(serverLevel)
    //}

    override fun getAmbientSound(): SoundEvent {
        return HSSoundEvents.OSTRICH_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HSSoundEvents.OSTRICH_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HSSoundEvents.PEACOCK_DIE.get()
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.FLYING_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }
    }
}