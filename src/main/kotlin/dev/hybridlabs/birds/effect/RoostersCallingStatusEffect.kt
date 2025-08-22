package dev.hybridlabs.birds.effect

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player

class RoostersCallingStatusEffect : MobEffect(MobEffectCategory.BENEFICIAL, 0x695672) {

    override fun isDurationEffectTick(duration: Int, amplifier: Int): Boolean {
        return duration % 40 == 0
    }

    override fun applyEffectTick(entity: LivingEntity, amplifier: Int) {
        if (entity.health < entity.maxHealth) {
            entity.heal(0.5f)
        }
        if (!entity.level().isClientSide) {
            (entity as Player).foodData.eat(amplifier + 1, 0.5f)
        }
    }
}