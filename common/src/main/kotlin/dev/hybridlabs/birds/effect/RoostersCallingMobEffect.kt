package dev.hybridlabs.birds.effect

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player

class RoostersCallingMobEffect : MobEffect(MobEffectCategory.BENEFICIAL, 0x695672) {

    override fun shouldApplyEffectTickThisTick(duration: Int, amplifier: Int): Boolean {
        return duration % 60 == 0
    }

    override fun applyEffectTick(entity: LivingEntity, amplifier: Int): Boolean {
        if (entity.health < entity.maxHealth) {
            entity.heal(0.5f)
            return true
        }
        if (!entity.level().isClientSide) {
            (entity as Player).foodData.eat(0, 0.5f)
            return true
        }
        return false
    }
}