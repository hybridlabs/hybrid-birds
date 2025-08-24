package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.entity.ai.BirdFloatControl
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import java.util.*
import kotlin.math.abs

class GoslingEntity(entityType: EntityType<out GoslingEntity>, world: Level) :
    HybridBirdsBirdEntity(entityType, world, true) {
    private var goslingNavigation = AmphibiousPathNavigation(this, world)
    private var goslingAge = 0

    init {
        moveControl = BirdFloatControl(this)
        navigation = goslingNavigation
    }

    override fun getWaterline(): Float {
        return 0.15f
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, FollowGooseGoal(this, 0.5))
        goalSelector.addGoal(0, PanicGoal(this, 0.6))
        goalSelector.addGoal(1, TemptGoal(this, 0.5, GooseEntity.BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, RandomStrollGoal(this, 0.5))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(11, LookAtPlayerGoal(this, Player::class.java, 10.0f))
    }

    override fun aiStep() {
        super.aiStep()
        if (!level().isClientSide) {
            this.setGoslingAge(this.goslingAge + 1)
        }
    }

    override fun addAdditionalSaveData(compoundTag: CompoundTag) {
        super.addAdditionalSaveData(compoundTag)
        compoundTag.putInt("Age", this.goslingAge)
    }

    override fun readAdditionalSaveData(compoundTag: CompoundTag) {
        super.readAdditionalSaveData(compoundTag)
        this.setGoslingAge(compoundTag.getInt("Age"))
    }

    private fun setGoslingAge(goslingAge: Int) {
        this.goslingAge = goslingAge
        if (this.goslingAge >= MAX_GOSLING_AGE) {
            this.growUp()
        }
    }

    private fun growUp() {
        val var2 = this.level()
        if (var2 is ServerLevel) {
            val grownEntityType = HybridBirdsEntityTypes.GOOSE
            val grownEntity = grownEntityType!!.get().create(this.level())

            if (grownEntity != null) {
                grownEntity.moveTo(this.x, this.y, this.z, this.yRot, this.xRot)
                grownEntity.finalizeSpawn(
                    var2,
                    level().getCurrentDifficultyAt(grownEntity.blockPosition()),
                    MobSpawnType.CONVERSION,
                    null as SpawnGroupData?,
                    null as CompoundTag?
                )
                grownEntity.isNoAi = this.isNoAi
                if (this.hasCustomName()) {
                    grownEntity.customName = this.customName
                    grownEntity.isCustomNameVisible = this.isCustomNameVisible
                }

                grownEntity.setPersistenceRequired()
                var2.addFreshEntityWithPassengers(grownEntity)
                this.discard()
            }
        }
    }

    override fun getAmbientSound(): SoundEvent {
        return HybridBirdsSoundEvents.GOSLING_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.GOSLING_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.GOSLING_DIE.get()
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        var MAX_GOSLING_AGE: Int = abs(-24000.0).toInt()
    }

    internal class FollowGooseGoal(mob: GoslingEntity, private val speed: Double) : Goal() {
        private val gosling: GoslingEntity = mob
        private var gooseEntity: GooseEntity? = null

        init {
            this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        }

        override fun canUse(): Boolean {
            val list = this.gosling.level().getEntitiesOfClass(
                GooseEntity::class.java,
                this.gosling.boundingBox.inflate(8.0, 4.0, 8.0)
            )
            var closestDistance = Double.MAX_VALUE

            for (goose in list) {
                val distance = this.gosling.distanceToSqr(goose)
                if (distance < closestDistance) {
                    closestDistance = distance
                    this.gooseEntity = goose
                }
            }

            return this.gooseEntity != null && closestDistance >= 1.5
        }

        override fun canContinueToUse(): Boolean {
            return this.gooseEntity != null && this.gosling.distanceToSqr(this.gooseEntity!!) >= 9.0
        }

        override fun start() {
            this.gosling.navigation.moveTo(this.gooseEntity!!, this.speed)
        }

        override fun stop() {
            this.gooseEntity = null
            this.gosling.navigation.stop()
        }

        override fun tick() {
            if (this.gosling.distanceToSqr(this.gooseEntity!!) >= 49.0) {
                this.gosling.navigation.moveTo(this.gooseEntity!!, this.speed)
            }
        }
    }
}