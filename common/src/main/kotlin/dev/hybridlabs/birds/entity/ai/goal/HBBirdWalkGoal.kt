package dev.hybridlabs.birds.entity.ai.goal

import dev.hybridlabs.birds.entity.bird.HBBirdEntity
import net.minecraft.world.entity.ai.goal.Goal

class HBBirdWalkGoal(
    private val bird: HBBirdEntity,
) : Goal() {

    override fun canUse(): Boolean {
        TODO("Not yet implemented")
    }
}