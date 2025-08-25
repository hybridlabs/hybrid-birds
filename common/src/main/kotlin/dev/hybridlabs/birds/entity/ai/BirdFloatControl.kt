package dev.hybridlabs.birds.entity.ai

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.phys.Vec3
import kotlin.math.max

// credit to fowl play for the code

class BirdFloatControl(bird: HybridBirdsBirdEntity) : MoveControl(bird) {
    override fun tick() {
        var deltaMovement: Vec3 = this.mob.deltaMovement
        if ((this.mob as HybridBirdsBirdEntity).isBelowWaterline()) {
            this.mob.deltaMovement = deltaMovement.add(0.0, 0.05, 0.0)
            if (this.mob.isUnderWater)
                deltaMovement = this.mob.deltaMovement
                this.mob.deltaMovement = deltaMovement.add(0.0, 0.05, 0.0)
            deltaMovement = this.mob.deltaMovement
            this.mob.setDeltaMovement(deltaMovement.x, max(deltaMovement.y, 0.0), deltaMovement.z)
        }
        super.tick()
    }
}