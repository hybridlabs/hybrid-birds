package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.sound.HBSoundEvents
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class FlamingoEntity(type: EntityType<out FlamingoEntity>, world: Level) :
    HBWadingBirdEntity(type, world) {

    //override fun getBreedOffspring(serverLevel: ServerLevel, ageableMob: AgeableMob): FlamingoEntity? {
    //    return HBEntityTypes.FLAMINGO.get().create(serverLevel)
    //}

    override fun getAmbientSound(): SoundEvent {
        return HBSoundEvents.DUCK_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HBSoundEvents.DUCK_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HBSoundEvents.DUCK_DIE.get()
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
    }
}