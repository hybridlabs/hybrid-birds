package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.entity.ai.BirdFloatControl
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.AmphibiousSwimNavigation
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World
import java.util.*
import kotlin.math.abs

class GoslingEntity(entityType: EntityType<out GoslingEntity>, world: World) :
    HybridBirdsBirdEntity(entityType, world) {
    private var goslingNavigation = AmphibiousSwimNavigation(this, world)
    private var goslingAge = 0

    init {
        moveControl = BirdFloatControl(this)
        navigation = goslingNavigation
    }

    override fun getWaterline(): Float {
        return 0.15f
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(0, FollowGooseGoal(this, 0.5))
        goalSelector.add(0, EscapeDangerGoal(this, 0.6))
        goalSelector.add(1, TemptGoal(this, 0.5, GooseEntity.BREEDING_INGREDIENT, false))
        goalSelector.add(2, WanderAroundGoal(this, 0.5))
        goalSelector.add(2, LookAroundGoal(this))
        goalSelector.add(11, LookAtEntityGoal(this, PlayerEntity::class.java, 10.0f))
    }

    override fun tickMovement() {
        super.tickMovement()
        if (!world.isClient) {
            this.setgoslingAge(this.goslingAge + 1)
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("Age", this.goslingAge)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.setgoslingAge(nbt.getInt("Age"))
    }

    private fun setgoslingAge(goslingAge: Int) {
        this.goslingAge = goslingAge
        if (this.goslingAge >= MAX_GOSLING_AGE) {
            this.growUp()
        }
    }

    private fun growUp() {
        val var2 = this.world
        if (var2 is ServerWorld) {
            val grownEntityType = HybridBirdsEntityTypes.GOOSE
            val grownEntity = grownEntityType.create(this.world)

            if (grownEntity != null) {
                grownEntity.refreshPositionAndAngles(this.x, this.y, this.z, this.yaw, this.pitch)
                grownEntity.initialize(
                    var2,
                    world.getLocalDifficulty(grownEntity.blockPos),
                    SpawnReason.CONVERSION,
                    null as EntityData?,
                    null as NbtCompound?
                )
                grownEntity.isAiDisabled = this.isAiDisabled
                if (this.hasCustomName()) {
                    grownEntity.customName = this.customName
                    grownEntity.isCustomNameVisible = this.isCustomNameVisible
                }

                grownEntity.setPersistent()
                var2.spawnEntityAndPassengers(grownEntity)
                this.discard()
            }
        }
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }

        var MAX_GOSLING_AGE: Int = abs(-24000.0).toInt()
    }

    internal class FollowGooseGoal(mob: GoslingEntity, private val speed: Double) : Goal() {
        private val gosling: GoslingEntity = mob
        private var gooseEntity: GooseEntity? = null

        init {
            this.controls = EnumSet.of(Control.MOVE, Control.LOOK)
        }

        override fun canStart(): Boolean {
            val list = this.gosling.world.getNonSpectatingEntities(
                GooseEntity::class.java,
                this.gosling.boundingBox.expand(8.0, 4.0, 8.0)
            )
            var closestDistance = Double.MAX_VALUE

            for (goose in list) {
                val distance = this.gosling.squaredDistanceTo(goose)
                if (distance < closestDistance) {
                    closestDistance = distance
                    this.gooseEntity = goose
                }
            }

            return this.gooseEntity != null && closestDistance >= 1.5
        }

        override fun shouldContinue(): Boolean {
            return this.gooseEntity != null && this.gosling.squaredDistanceTo(this.gooseEntity!!) >= 9.0
        }

        override fun start() {
            this.gosling.navigation.startMovingTo(this.gooseEntity, this.speed)
        }

        override fun stop() {
            this.gooseEntity = null
            this.gosling.navigation.stop()
        }

        override fun tick() {
            if (this.gosling.squaredDistanceTo(this.gooseEntity) >= 49.0) {
                this.gosling.navigation.startMovingTo(this.gooseEntity, this.speed)
            }
        }
    }
}