package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.HybridBirdsParrotEntity
import dev.hybridlabs.birds.entity.bird.HybridBirdsRatiteEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridBirdsRatiteEntityModel<T: HybridBirdsRatiteEntity> (private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/$id.animation.json")
    }

    override fun setCustomAnimations(animatable: T, instanceId: Long, animationState: AnimationState<T>) {
        val head = animationProcessor.getBone("head")
        val neck = animationProcessor.getBone("neck")

        if (head != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            head.rotX = entityData.headPitch() * Mth.DEG_TO_RAD
            head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
        }

        if (neck != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            neck.rotX = (entityData.headPitch() * Mth.DEG_TO_RAD) / 2
            neck.rotY = (entityData.netHeadYaw() * Mth.DEG_TO_RAD) / 2
        }
    }
}