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

class CygnetEntity(entityType: EntityType<out CygnetEntity>, world: Level) :
    HybridBirdsBirdEntity(entityType, world, true) {
    private var cygnetNavigation = AmphibiousPathNavigation(this, world)
    private var cygnetAge = 0

    init {
        moveControl = BirdFloatControl(this)
        navigation = cygnetNavigation
    }

    override fun getWaterline(): Float {
        return 0.2f
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, FollowSwanGoal(this, 0.5))
        goalSelector.addGoal(0, PanicGoal(this, 0.6))
        goalSelector.addGoal(1, TemptGoal(this, 0.5, SwanEntity.BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, RandomStrollGoal(this, 0.5))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(11, LookAtPlayerGoal(this, Player::class.java, 10.0f))
    }

    override fun aiStep() {
        super.aiStep()
        if (!level().isClientSide) {
            this.setCygnetAge(this.cygnetAge + 1)
        }
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt("Age", this.cygnetAge)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        this.setCygnetAge(nbt.getInt("Age"))
    }

    private fun setCygnetAge(cygnetAge: Int) {
        this.cygnetAge = cygnetAge
        if (this.cygnetAge >= MAX_CYGNET_AGE) {
            this.growUp()
        }
    }

    private fun growUp() {
        val var2 = this.level()
        if (var2 is ServerLevel) {
            val grownEntityType = HybridBirdsEntityTypes.SWAN
            val grownEntity = grownEntityType.create(this.level())

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
        return HybridBirdsSoundEvents.CYGNET_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.CYGNET_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.CYGNET_DIE
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        var MAX_CYGNET_AGE: Int = abs(-24000.0).toInt()
    }

    internal class FollowSwanGoal(mob: CygnetEntity, private val speed: Double) : Goal() {
        private val cygnet: CygnetEntity = mob
        private var swanEntity: SwanEntity? = null

        init {
            this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        }

        override fun canUse(): Boolean {
            val list = this.cygnet.level().getEntitiesOfClass(
                SwanEntity::class.java,
                this.cygnet.boundingBox.inflate(8.0, 4.0, 8.0)
            )
            var closestDistance = Double.MAX_VALUE

            for (swan in list) {
                val distance = this.cygnet.distanceToSqr(swan)
                if (distance < closestDistance) {
                    closestDistance = distance
                    this.swanEntity = swan
                }
            }

            return this.swanEntity != null && closestDistance >= 1.5
        }

        override fun canContinueToUse(): Boolean {
            return this.swanEntity != null && this.cygnet.distanceToSqr(this.swanEntity!!) >= 9.0
        }

        override fun start() {
            this.cygnet.navigation.moveTo(this.swanEntity, this.speed)
        }

        override fun stop() {
            this.swanEntity = null
            this.cygnet.navigation.stop()
        }

        override fun tick() {
            if (this.cygnet.distanceToSqr(this.swanEntity) >= 49.0) {
                this.cygnet.navigation.moveTo(this.swanEntity, this.speed)
            }
        }
    }
}