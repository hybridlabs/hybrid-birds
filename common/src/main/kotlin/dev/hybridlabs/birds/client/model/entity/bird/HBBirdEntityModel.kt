package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.HBBirdEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HBBirdEntityModel<T : HBBirdEntity>(private val id: String) : GeoModel<T>() {

    override fun getModelResource(animatable: T): ResourceLocation {
        return if (animatable.isBaby) {
            ResourceLocation(Constants.MOD_ID, "geo/baby_$id.geo.json")
        } else {
            ResourceLocation(Constants.MOD_ID, "geo/$id.geo.json")
        }
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return if (animatable.isBaby) {
            ResourceLocation(Constants.MOD_ID, "textures/entity/baby_$id.png")
        } else {
            ResourceLocation(Constants.MOD_ID, "textures/entity/$id.png")
        }
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return if (animatable.isBaby) {
            ResourceLocation(Constants.MOD_ID, "animations/baby_$id.animation.json")
        } else {
            ResourceLocation(Constants.MOD_ID, "animations/$id.animation.json")
        }
    }

    override fun setCustomAnimations(animatable: T, instanceId: Long, animationState: AnimationState<T>) {
        val head = animationProcessor.getBone("head")

        if (head != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            head.rotX = entityData.headPitch() * Mth.DEG_TO_RAD
            head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
        }
    }
}