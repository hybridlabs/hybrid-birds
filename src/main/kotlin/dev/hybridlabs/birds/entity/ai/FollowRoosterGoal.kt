package dev.hybridlabs.birds.entity.ai

import dev.hybridlabs.birds.entity.bird.RoosterEntity
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import net.minecraft.world.entity.animal.Chicken
import java.util.*

class FollowRoosterGoal(
    private val chicken: Chicken,
    private val speed: Double,
    private val minDistance: Float,
    private val maxDistance: Float
) : Goal() {
    private var rooster: RoosterEntity? = null
    private var delayCounter = 0
    private val targetPredicate = TargetingConditions.forNonCombat().range(maxDistance.toDouble())

    init {
        this.flags = EnumSet.of(Flag.MOVE)
    }

    override fun canUse(): Boolean {
        val world = chicken.level()
        val nearestRooster: RoosterEntity? = world.getNearestEntity(
            RoosterEntity::class.java,
            targetPredicate,
            chicken,
            chicken.x,
            chicken.y,
            chicken.z,
            chicken.boundingBox.inflate(maxDistance.toDouble())
        )

        if (nearestRooster == null || nearestRooster.distanceToSqr(chicken) < minDistance * minDistance) {
            return false
        }

        this.rooster = nearestRooster
        return true
    }

    override fun canContinueToUse(): Boolean {
        val rooster = rooster ?: return false

        if (!rooster.isAlive) {
            return false
        }

        return chicken.distanceToSqr(rooster) > minDistance * minDistance && chicken.distanceToSqr(rooster) < maxDistance * maxDistance
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

                val targetPos = rooster.position()
                val x = targetPos.x
                val y = targetPos.y
                val z = targetPos.z
                chicken.navigation.moveTo(x.toDouble(), y.toDouble(), z.toDouble(), speed)
            }
        }
    }
}
