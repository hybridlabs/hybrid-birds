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

class KeetEntity(entityType: EntityType<out KeetEntity>, world: World) :
    BirdEntity(entityType, world) {
    private var keetNavigation: EntityNavigation = createNavigation(world)
    private var keetAge = 0

    init {
        moveControl = MoveControl(this)
        navigation = keetNavigation
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(0, FollowDuckGoal(this, 0.6))
        goalSelector.add(0, EscapeDangerGoal(this, 0.6))
        goalSelector.add(1, TemptGoal(this, 0.6, Ingredient.fromTag(ItemTags.VILLAGER_PLANTABLE_SEEDS), false))
        goalSelector.add(2, WanderAroundGoal(this, 0.5))
        goalSelector.add(2, LookAroundGoal(this))
        goalSelector.add(11, LookAtEntityGoal(this, PlayerEntity::class.java, 10.0f))
    }

    override fun tickMovement() {
        super.tickMovement()
        if (!world.isClient) {
            this.setDucklingAge(this.keetAge + 1)
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("Age", this.keetAge)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.setDucklingAge(nbt.getInt("Age"))
    }

    private fun setDucklingAge(keetAge: Int) {
        this.keetAge = keetAge
        if (this.keetAge >= MAX_KEET_AGE) {
            this.growUp()
        }
    }

    private fun growUp() {
        val var2 = this.world
        if (var2 is ServerWorld) {
            val grownEntityType = HybridBirdsEntityTypes.GUINEA_FOWL
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

        var MAX_KEET_AGE: Int = abs(-24000.0).toInt()
    }

    internal class FollowDuckGoal(mob: KeetEntity, private val speed: Double) : Goal() {
        private val keet: KeetEntity = mob
        private var guineaFowlEntity: DuckEntity? = null

        init {
            this.controls = EnumSet.of(Control.MOVE, Control.LOOK)
        }

        override fun canStart(): Boolean {
            val list = this.keet.world.getNonSpectatingEntities(
                DuckEntity::class.java,
                this.keet.boundingBox.expand(8.0, 4.0, 8.0)
            )
            var closestDistance = Double.MAX_VALUE

            for (guineaFowl in list) {
                val distance = this.keet.squaredDistanceTo(guineaFowl)
                if (distance < closestDistance) {
                    closestDistance = distance
                    this.guineaFowlEntity = guineaFowl
                }
            }

            return this.guineaFowlEntity != null && closestDistance >= 1.5
        }

        override fun shouldContinue(): Boolean {
            return this.guineaFowlEntity != null && this.keet.squaredDistanceTo(this.guineaFowlEntity!!) >= 9.0
        }

        override fun start() {
            this.keet.navigation.startMovingTo(this.guineaFowlEntity, this.speed)
        }

        override fun stop() {
            this.guineaFowlEntity = null
            this.keet.navigation.stop()
        }

        override fun tick() {
            if (this.keet.squaredDistanceTo(this.guineaFowlEntity!!) >= 49.0) {
                this.keet.navigation.startMovingTo(this.guineaFowlEntity, this.speed)
            }
        }
    }
}
