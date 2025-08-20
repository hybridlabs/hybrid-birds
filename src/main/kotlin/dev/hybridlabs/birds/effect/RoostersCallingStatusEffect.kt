package dev.hybridlabs.birds.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity

class RoostersCallingStatusEffect : StatusEffect(StatusEffectCategory.BENEFICIAL, 0x695672) {

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return duration % 40 == 0
    }

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int): Boolean {
        if (entity.health < entity.maxHealth) {
            entity.heal(0.5f)
        }
        if (!entity.world.isClient) {
            (entity as PlayerEntity).hungerManager.add(amplifier + 1, 0.5f)
        }
        return true
    }
}