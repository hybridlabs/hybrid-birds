package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.EscapeDangerGoal
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.ai.goal.TemptGoal
import net.minecraft.entity.ai.goal.WanderAroundGoal
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.passive.ChickenEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.ItemTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.world.World
import java.util.EnumSet
import kotlin.math.abs
import kotlin.random.Random

class ChickEntity(entityType: EntityType<out ChickEntity>, world: World) :
    HybridBirdsBirdEntity(entityType, world, false) {
    private var chickNavigation: EntityNavigation = createNavigation(world)
    private var chickAge = 0

    init {
        moveControl = MoveControl(this)
        navigation = chickNavigation
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
        goalSelector.add(0, FollowChickenGoal(this, 0.6))
        goalSelector.add(0, EscapeDangerGoal(this, 0.6))
        goalSelector.add(1, TemptGoal(this, 0.6, Ingredient.fromTag(ItemTags.VILLAGER_PLANTABLE_SEEDS), false))
        goalSelector.add(2, WanderAroundGoal(this, 0.5))
        goalSelector.add(2, LookAroundGoal(this))
        goalSelector.add(11, LookAtEntityGoal(this, PlayerEntity::class.java, 10.0f))
    }

    override fun tickMovement() {
        super.tickMovement()
        if (!world.isClient) {
            this.setChickAge(this.chickAge + 1)
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("Age", this.chickAge)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.setChickAge(nbt.getInt("Age"))
    }

    private fun setChickAge(chickAge: Int) {
        this.chickAge = chickAge
        if (this.chickAge >= MAX_CHICK_AGE) {
            this.growUp()
        }
    }

    private fun growUp() {
        val var2 = this.world
        if (var2 is ServerWorld) {
            val isRooster = Random.nextFloat() < 0.25
            val grownEntityType = if (isRooster) HybridBirdsEntityTypes.ROOSTER else EntityType.CHICKEN
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

    override fun getAmbientSound(): SoundEvent {
        return HybridBirdsSoundEvents.CHICK_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.CHICK_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.CHICK_DIE
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }

        var MAX_CHICK_AGE: Int = abs(-24000.0).toInt()
    }

    internal class FollowChickenGoal(mob: ChickEntity, private val speed: Double) : Goal() {
        private val chick: ChickEntity = mob
        private var chickenEntity: ChickenEntity? = null

        init {
            this.controls = EnumSet.of(Control.MOVE, Control.LOOK)
        }

        override fun canStart(): Boolean {
            val list = this.chick.world.getNonSpectatingEntities(
                ChickenEntity::class.java,
                this.chick.boundingBox.expand(8.0, 4.0, 8.0)
            )
            var closestDistance = Double.MAX_VALUE

            for (chicken in list) {
                val distance = this.chick.squaredDistanceTo(chicken)
                if (distance < closestDistance) {
                    closestDistance = distance
                    this.chickenEntity = chicken
                }
            }

            return this.chickenEntity != null && closestDistance >= 1.5
        }

        override fun shouldContinue(): Boolean {
            return this.chickenEntity != null && this.chick.squaredDistanceTo(this.chickenEntity!!) >= 9.0
        }

        override fun start() {
            this.chick.navigation.startMovingTo(this.chickenEntity, this.speed)
        }

        override fun stop() {
            this.chickenEntity = null
            this.chick.navigation.stop()
        }

        override fun tick() {
            if (this.chick.squaredDistanceTo(this.chickenEntity) >= 49.0) {
                this.chick.navigation.startMovingTo(this.chickenEntity, this.speed)
            }
        }
    }
}