package dev.hybridlabs.birds.entity.ai

import dev.hybridlabs.birds.entity.bird.RoosterEntity
import net.minecraft.entity.ai.TargetPredicate
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.passive.ChickenEntity
import java.util.EnumSet

class FollowRoosterGoal(
    private val chicken: ChickenEntity,
    private val speed: Double,
    private val minDistance: Float,
    private val maxDistance: Float
) : Goal() {
    private var rooster: RoosterEntity? = null
    private var delayCounter = 0
    private val targetPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(maxDistance.toDouble())

    init {
        this.controls = EnumSet.of(Control.MOVE)
    }

    override fun canStart(): Boolean {
        val world = chicken.world
        val nearestRooster: RoosterEntity? = world.getClosestEntity(
            RoosterEntity::class.java,
            targetPredicate,
            chicken,
            chicken.x,
            chicken.y,
            chicken.z,
            chicken.boundingBox.expand(maxDistance.toDouble())
        )

        if (nearestRooster == null || nearestRooster.squaredDistanceTo(chicken) < minDistance * minDistance) {
            return false
        }

        this.rooster = nearestRooster
        return true
    }

    override fun shouldContinue(): Boolean {
        val rooster = rooster ?: return false

        if (!rooster.isAlive) {
            return false
        }

        return chicken.squaredDistanceTo(rooster) > minDistance * minDistance && chicken.squaredDistanceTo(rooster) < maxDistance * maxDistance
    }

    override fun start() {
        delayCounter = 0
    }

    override fun stop() {
        rooster = null
    }

    override fun tick() {
        rooster?.let { rooster ->
            if (!rooster.isAlive) {
                return@let
            }

            if (--delayCounter <= 0) {
                delayCounter = 10

                val targetPos = rooster.blockPos
                val x = targetPos.x
                val y = targetPos.y
                val z = targetPos.z
                chicken.navigation.startMovingTo(x.toDouble(), y.toDouble(), z.toDouble(), speed)
            }
        }
    }
}
