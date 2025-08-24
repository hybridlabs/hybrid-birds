package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
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
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import java.util.*
import kotlin.math.abs

class PeachickEntity(entityType: EntityType<out PeachickEntity>, world: Level) :
    HybridBirdsBirdEntity(entityType, world, false) {
    private var peachickNavigation: PathNavigation = createNavigation(world)
    private var peachickAge = 0

    init {
        moveControl = MoveControl(this)
        navigation = peachickNavigation
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals(){
        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(0, FollowPeacockGoal(this, 0.6))
        goalSelector.addGoal(0, PanicGoal(this, 0.6))
        goalSelector.addGoal(1, TemptGoal(this, 0.6, PeacockEntity.BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, RandomStrollGoal(this, 0.5))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(11, LookAtPlayerGoal(this, Player::class.java, 10.0f))
    }

    override fun aiStep() {
        super.aiStep()
        if (!level().isClientSide) {
            this.setPeachickAge(this.peachickAge + 1)
        }
    }

    override fun addAdditionalSaveData(compoundTag: CompoundTag) {
        super.addAdditionalSaveData(compoundTag)
        compoundTag.putInt("Age", this.peachickAge)
    }

    override fun readAdditionalSaveData(compoundTag: CompoundTag) {
        super.readAdditionalSaveData(compoundTag)
        this.setPeachickAge(compoundTag.getInt("Age"))
    }

    private fun setPeachickAge(peachickAge: Int) {
        this.peachickAge = peachickAge
        if (this.peachickAge >= MAX_PEACHICK_AGE) {
            this.growUp()
        }
    }

    private fun growUp() {
        val var2 = this.level()
        if (var2 is ServerLevel) {
            val grownEntityType = HybridBirdsEntityTypes.PEACOCK
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
        return HybridBirdsSoundEvents.PEACHICK_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.PEACHICK_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.PEACHICK_DIE.get()
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        var MAX_PEACHICK_AGE: Int = abs(-24000.0).toInt()
    }

    internal class FollowPeacockGoal(mob: PeachickEntity, private val speed: Double) : Goal() {
        private val peachick: PeachickEntity = mob
        private var peacockEntity: PeacockEntity? = null

        init {
            this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        }

        override fun canUse(): Boolean {
            val list = this.peachick.level().getEntitiesOfClass(
                PeacockEntity::class.java,
                this.peachick.boundingBox.inflate(8.0, 4.0, 8.0)
            )
            var closestDistance = Double.MAX_VALUE

            for (peacock in list) {
                val distance = this.peachick.distanceToSqr(peacock)
                if (distance < closestDistance) {
                    closestDistance = distance
                    this.peacockEntity = peacock
                }
            }

            return this.peacockEntity != null && closestDistance >= 1.5
        }

        override fun canContinueToUse(): Boolean {
            return this.peacockEntity != null && this.peachick.distanceToSqr(this.peacockEntity!!) >= 9.0
        }

        override fun start() {
            this.peachick.navigation.moveTo(this.peacockEntity!!, this.speed)
        }

        override fun stop() {
            this.peacockEntity = null
            this.peachick.navigation.stop()
        }

        override fun tick() {
            if (this.peachick.distanceToSqr(this.peacockEntity!!) >= 49.0) {
                this.peachick.navigation.moveTo(this.peacockEntity!!, this.speed)
            }
        }
    }
}