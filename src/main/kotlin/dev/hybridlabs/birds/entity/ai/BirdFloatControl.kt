package dev.hybridlabs.birds.entity.ai

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.util.math.Vec3d
import kotlin.math.max

class BirdFloatControl(bird: HybridBirdsBirdEntity) : MoveControl(bird) {
    override fun tick() {
        var velocity: Vec3d = this.entity.velocity
        if ((this.entity as HybridBirdsBirdEntity).isBelowWaterline()) {
            this.entity.velocity = velocity.add(0.0, 0.05, 0.0)
            if (this.entity.isSubmergedInWater) {
                velocity = this.entity.velocity
                this.entity.velocity = velocity.add(0.0, 0.05, 0.0)
            }
            velocity = this.entity.velocity
            this.entity.setVelocity(velocity.getX(), max(velocity.getY(), 0.0), velocity.getZ())
        }
        super.tick()
    }
}