package dev.hybridlabs.birds.entity.ai.goal

import dev.hybridlabs.birds.entity.bird.HBBirdEntity
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.level.Level
import java.util.*

open class BirdBreedGoal @JvmOverloads constructor(
    protected val bird: HBBirdEntity,
    private val speedModifier: Double,
    private val partnerClass: Class<out Animal> = bird.javaClass,
) : Goal() {
    protected val level: Level = bird.level()
    protected var partner: Animal? = null
    private var loveTime = 0

    init {
        this.flags = EnumSet.of<Flag?>(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        if (!this.bird.isInLove) {
            return false
        } else {
            this.partner = this.freePartner
            return this.partner != null
        }
    }

    override fun canContinueToUse(): Boolean {
        return this.partner!!.isAlive && this.partner!!.isInLove && this.loveTime < 60
    }

    override fun stop() {
        this.partner = null
        this.loveTime = 0
    }

    override fun tick() {
        this.bird.getLookControl().setLookAt(this.partner, 10.0f, this.bird.maxHeadXRot.toFloat())
        this.bird.getNavigation().moveTo(this.partner, this.speedModifier)
        ++this.loveTime
        if (this.loveTime >= this.adjustedTickDelay(60) && this.bird.distanceToSqr(this.partner) < 9.0) {
            this.breed()
        }
    }

    private val freePartner: Animal?
        get() {
            val list: MutableList<out Animal> = this.level.getNearbyEntities(
                this.partnerClass,
                PARTNER_TARGETING,
                this.bird,
                this.bird.boundingBox.inflate(8.0)
            )
            var d0 = Double.MAX_VALUE
            var animal: Animal? = null

            for (animal1 in list) {
                if (this.bird.canMate(animal1) && this.bird.distanceToSqr(animal1) < d0) {
                    animal = animal1
                    d0 = this.bird.distanceToSqr(animal1)
                }
            }

            return animal
        }

    protected open fun breed() {
        this.bird.spawnChildFromBreeding(this.level as ServerLevel, this.partner)
    }

    companion object {
        private val PARTNER_TARGETING: TargetingConditions =
            TargetingConditions.forNonCombat().range(8.0).ignoreLineOfSight()
    }
}