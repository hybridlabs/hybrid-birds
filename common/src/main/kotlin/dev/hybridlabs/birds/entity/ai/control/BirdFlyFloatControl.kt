package dev.hybridlabs.birds.entity.ai.control

import dev.hybridlabs.birds.entity.bird.HBBirdEntity
import net.minecraft.world.entity.ai.control.FlyingMoveControl
import kotlin.math.max

// credit to fowl play for the code

class BirdFlyFloatControl(
    bird: HBBirdEntity,
    maxTurn: Int,
    hoversInPlace: Boolean
) : FlyingMoveControl(bird, maxTurn, hoversInPlace) {

    override fun tick() {
        val bird = this.mob as HBBirdEntity

        if (bird.isBelowWaterline()) {
            var delta = bird.deltaMovement

            delta = delta.add(0.0, 0.05, 0.0)

            if (bird.isUnderWater) {
                delta = delta.add(0.0, 0.05, 0.0)
            }

            bird.setDeltaMovement(
                delta.x,
                max(delta.y, 0.0),
                delta.z
            )
        }
        super.tick()
    }
}
