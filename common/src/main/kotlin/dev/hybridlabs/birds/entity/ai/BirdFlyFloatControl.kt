package dev.hybridlabs.birds.entity.ai

import dev.hybridlabs.birds.entity.bird.HBBirdEntity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.control.FlyingMoveControl
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.phys.Vec3
import kotlin.math.max

// credit to fowl play for the code

class BirdFlyFloatControl(
    bird: Mob,
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
