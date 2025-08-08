package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
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

class PoultEntity(entityType: EntityType<out PoultEntity>, world: World) :
    HybridBirdsBirdEntity(entityType, world) {
    private var poultNavigation: EntityNavigation = createNavigation(world)
    private var poultAge = 0

    init {
        moveControl = MoveControl(this)
        navigation = poultNavigation
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(0, FollowGooseGoal(this, 0.6))
        goalSelector.add(0, EscapeDangerGoal(this, 0.6))
        goalSelector.add(1, TemptGoal(this, 0.6, Ingredient.fromTag(ItemTags.VILLAGER_PLANTABLE_SEEDS), false))
        goalSelector.add(2, WanderAroundGoal(this, 0.5))
        goalSelector.add(2, LookAroundGoal(this))
        goalSelector.add(11, LookAtEntityGoal(this, PlayerEntity::class.java, 10.0f))
    }

    override fun tickMovement() {
        super.tickMovement()
        if (!world.isClient) {
            this.setpoultAge(this.poultAge + 1)
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("Age", this.poultAge)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.setpoultAge(nbt.getInt("Age"))
    }

    private fun setpoultAge(poultAge: Int) {
        this.poultAge = poultAge
        if (this.poultAge >= MAX_POULT_AGE) {
            this.growUp()
        }
    }

    private fun growUp() {
        val var2 = this.world
        if (var2 is ServerWorld) {
            val grownEntityType = HybridBirdsEntityTypes.TURKEY
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

        var MAX_POULT_AGE: Int = abs(-24000.0).toInt()
    }

    internal class FollowGooseGoal(mob: PoultEntity, private val speed: Double) : Goal() {
        private val poult: PoultEntity = mob
        private var turkeyEntity: GooseEntity? = null

        init {
            this.controls = EnumSet.of(Control.MOVE, Control.LOOK)
        }

        override fun canStart(): Boolean {
            val list = this.poult.world.getNonSpectatingEntities(
                GooseEntity::class.java,
                this.poult.boundingBox.expand(8.0, 4.0, 8.0)
            )
            var closestDistance = Double.MAX_VALUE

            for (turkey in list) {
                val distance = this.poult.squaredDistanceTo(turkey)
                if (distance < closestDistance) {
                    closestDistance = distance
                    this.turkeyEntity = turkey
                }
            }

            return this.turkeyEntity != null && closestDistance >= 1.5
        }

        override fun shouldContinue(): Boolean {
            return this.turkeyEntity != null && this.poult.squaredDistanceTo(this.turkeyEntity!!) >= 9.0
        }

        override fun start() {
            this.poult.navigation.startMovingTo(this.turkeyEntity, this.speed)
        }

        override fun stop() {
            this.turkeyEntity = null
            this.poult.navigation.stop()
        }

        override fun tick() {
            if (this.poult.squaredDistanceTo(this.turkeyEntity!!) >= 49.0) {
                this.poult.navigation.startMovingTo(this.turkeyEntity, this.speed)
            }
        }
    }
}