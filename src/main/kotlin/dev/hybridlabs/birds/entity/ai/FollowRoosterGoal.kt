package dev.hybridlabs.birds.entity.ai

import dev.hybridlabs.birds.entity.bird.RoosterEntity
import net.minecraft.entity.ai.TargetPredicate
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.passive.ChickenEntity
import java.util.*

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
        val nearestRooster: RoosterEntity? = chicken.world.getClosestEntity(
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
        return rooster != null && rooster!!.isAlive &&
                chicken.squaredDistanceTo(rooster!!) > minDistance * minDistance &&
                chicken.squaredDistanceTo(rooster!!) < maxDistance * maxDistance
    }

    override fun start() {
        delayCounter = 0
    }

    override fun stop() {
        rooster = null
    }

    override fun tick() {
        if (rooster == null || !rooster!!.isAlive) {
            return
        }

        if (--delayCounter <= 0) {
            delayCounter = 10
            val targetPos = rooster!!.blockPos
            chicken.navigation.startMovingTo(
                targetPos.x.toDouble(), targetPos.y.toDouble(), targetPos.z.toDouble(), speed
            )
        }
    }
}
