package dev.hybridlabs.birds.entity.ai.goal

import dev.hybridlabs.birds.entity.bird.HBBirdEntity
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal

class HBRandomFlyingGoal(
    private val bird: HBBirdEntity
    ) : WaterAvoidingRandomFlyingGoal(
    bird,
        1.0
    ) {

    override fun canUse(): Boolean {
        if (bird.isClipped) return false
        return super.canUse()
    }

    override fun canContinueToUse(): Boolean {
        if (bird.isClipped) return false
        return super.canContinueToUse()
    }
}