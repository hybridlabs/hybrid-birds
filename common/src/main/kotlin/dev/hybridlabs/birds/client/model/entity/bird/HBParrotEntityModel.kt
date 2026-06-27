package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.HBParrotEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
abstract class HBParrotEntityModel<T: HBParrotEntity> (private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "animations/$id.animation.json")
    }

    override fun setCustomAnimations(animatable: T, instanceId: Long, animationState: AnimationState<T>) {
        val head = animationProcessor.getBone("head")

        if (head != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            head.rotX = entityData!!.headPitch() * Mth.DEG_TO_RAD
            head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
        }
    }
}