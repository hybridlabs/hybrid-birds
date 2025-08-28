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

class KeetEntity(entityType: EntityType<out KeetEntity>, world: Level) :
    HybridBirdsBirdEntity(entityType, world, false) {
    private var keetNavigation: PathNavigation = createNavigation(world)
    private var keetAge = 0

    init {
        moveControl = MoveControl(this)
        navigation = keetNavigation
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(0, FollowGuineaFowlGoal(this, 0.6))
        goalSelector.addGoal(0, PanicGoal(this, 0.6))
        goalSelector.addGoal(1, TemptGoal(this, 0.6, GuineaFowlEntity.BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, RandomStrollGoal(this, 0.5))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(11, LookAtPlayerGoal(this, Player::class.java, 10.0f))
    }

    override fun aiStep() {
        super.aiStep()
        if (!level().isClientSide) {
            this.setKeetAge(this.keetAge + 1)
        }
    }

    override fun addAdditionalSaveData(compoundTag: CompoundTag) {
        super.addAdditionalSaveData(compoundTag)
        compoundTag.putInt("Age", this.keetAge)
    }

    override fun readAdditionalSaveData(compoundTag: CompoundTag) {
        super.readAdditionalSaveData(compoundTag)
        this.setKeetAge(compoundTag.getInt("Age"))
    }

    private fun setKeetAge(keetAge: Int) {
        this.keetAge = keetAge
        if (this.keetAge >= MAX_KEET_AGE) {
            this.growUp()
        }
    }

    private fun growUp() {
        val var2 = this.level()
        if (var2 is ServerLevel) {
            val grownEntityType = HybridBirdsEntityTypes.GUINEA_FOWL
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
        return HybridBirdsSoundEvents.CHICK_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HybridBirdsSoundEvents.CHICK_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HybridBirdsSoundEvents.CHICK_DIE.get()
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        var MAX_KEET_AGE: Int = abs(-24000.0).toInt()
    }

    internal class FollowGuineaFowlGoal(mob: KeetEntity, private val speed: Double) : Goal() {
        private val keet: KeetEntity = mob
        private var guineaFowlEntity: GuineaFowlEntity? = null

        init {
            this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        }

        override fun canUse(): Boolean {
            val list = this.keet.level().getEntitiesOfClass(
                GuineaFowlEntity::class.java,
                this.keet.boundingBox.inflate(8.0, 4.0, 8.0)
            )
            var closestDistance = Double.MAX_VALUE

            for (guineaFowl in list) {
                val distance = this.keet.distanceToSqr(guineaFowl)
                if (distance < closestDistance) {
                    closestDistance = distance
                    this.guineaFowlEntity = guineaFowl
                }
            }

            return this.guineaFowlEntity != null && closestDistance >= 1.5
        }

        override fun canContinueToUse(): Boolean {
            return this.guineaFowlEntity != null && this.keet.distanceToSqr(this.guineaFowlEntity!!) >= 9.0
        }

        override fun start() {
            this.keet.navigation.moveTo(this.guineaFowlEntity!!, this.speed)
        }

        override fun stop() {
            this.guineaFowlEntity = null
            this.keet.navigation.stop()
        }

        override fun tick() {
            if (this.keet.distanceToSqr(this.guineaFowlEntity!!) >= 49.0) {
                this.keet.navigation.moveTo(this.guineaFowlEntity!!, this.speed)
            }
        }
    }
}