package dev.hybridlabs.birds.entity.bird

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.tags.ItemTags
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.FollowMobGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import kotlin.math.abs
import kotlin.random.Random

class ChickEntity(entityType: EntityType<out ChickEntity>, world: Level) :
    HBBirdEntity(entityType, world) {
    private var chickAge = 0

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, FollowMobGoal(this, 1.0, 1.0f, 6.0f))
        goalSelector.addGoal(1, TemptGoal(this, 1.0, Ingredient.of(ItemTags.VILLAGER_PLANTABLE_SEEDS), false))
    }

    override fun aiStep() {
        super.aiStep()
        if (!level().isClientSide) {
            this.setChickAge(this.chickAge + 1)
        }
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt("Age", this.chickAge)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        this.setChickAge(nbt.getInt("Age"))
    }

    private fun setChickAge(chickAge: Int) {
        this.chickAge = chickAge
        if (this.chickAge >= MAX_CHICK_AGE) {
            this.growUp()
        }
    }

    private fun growUp() {
        val var2 = this.level()
        if (var2 is ServerLevel) {
            val isRooster = Random.nextFloat() < 0.5
            val grownEntityType = if (isRooster) HybridBirdsEntityTypes.ROOSTER!!.get() else EntityType.CHICKEN
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
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        var MAX_CHICK_AGE: Int = abs(-24000.0).toInt()
    }
}