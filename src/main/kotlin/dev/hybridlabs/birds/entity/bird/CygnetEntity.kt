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
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.ItemTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World
import java.util.*
import kotlin.math.abs

class CygnetEntity(entityType: EntityType<out CygnetEntity>, world: World) :
    HybridBirdsBirdEntity(entityType, world) {
    private var cygnetNavigation = AmphibiousSwimNavigation(this, world)
    private var cygnetAge = 0

    init {
        moveControl = BirdFloatControl(this)
        navigation = cygnetNavigation
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(0, FollowSwanGoal(this, 0.6))
        goalSelector.add(0, EscapeDangerGoal(this, 0.6))
        goalSelector.add(1, TemptGoal(this, 0.6, SwanEntity.BREEDING_INGREDIENT, false))
        goalSelector.add(2, WanderAroundGoal(this, 0.5))
        goalSelector.add(2, LookAroundGoal(this))
        goalSelector.add(11, LookAtEntityGoal(this, PlayerEntity::class.java, 10.0f))
    }

    override fun tickMovement() {
        super.tickMovement()
        if (!world.isClient) {
            this.setcygnetAge(this.cygnetAge + 1)
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("Age", this.cygnetAge)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.setcygnetAge(nbt.getInt("Age"))
    }

    private fun setcygnetAge(cygnetAge: Int) {
        this.cygnetAge = cygnetAge
        if (this.cygnetAge >= MAX_CYGNET_AGE) {
            this.growUp()
        }
    }

    private fun growUp() {
        val var2 = this.world
        if (var2 is ServerWorld) {
            val grownEntityType = HybridBirdsEntityTypes.SWAN
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

        var MAX_CYGNET_AGE: Int = abs(-24000.0).toInt()
    }

    internal class FollowSwanGoal(mob: CygnetEntity, private val speed: Double) : Goal() {
        private val cygnet: CygnetEntity = mob
        private var swanEntity: SwanEntity? = null

        init {
            this.controls = EnumSet.of(Control.MOVE, Control.LOOK)
        }

        override fun canStart(): Boolean {
            val list = this.cygnet.world.getNonSpectatingEntities(
                SwanEntity::class.java,
                this.cygnet.boundingBox.expand(8.0, 4.0, 8.0)
            )
            var closestDistance = Double.MAX_VALUE

            for (swan in list) {
                val distance = this.cygnet.squaredDistanceTo(swan)
                if (distance < closestDistance) {
                    closestDistance = distance
                    this.swanEntity = swan
                }
            }

            return this.swanEntity != null && closestDistance >= 1.5
        }

        override fun shouldContinue(): Boolean {
            return this.swanEntity != null && this.cygnet.squaredDistanceTo(this.swanEntity!!) >= 9.0
        }

        override fun start() {
            this.cygnet.navigation.startMovingTo(this.swanEntity, this.speed)
        }

        override fun stop() {
            this.swanEntity = null
            this.cygnet.navigation.stop()
        }

        override fun tick() {
            if (this.cygnet.squaredDistanceTo(this.swanEntity!!) >= 49.0) {
                this.cygnet.navigation.startMovingTo(this.swanEntity, this.speed)
            }
        }
    }
}