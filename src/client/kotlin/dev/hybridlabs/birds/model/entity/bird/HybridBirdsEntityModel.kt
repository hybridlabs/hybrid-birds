package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.HybridBirds
import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.model.GeoModel

abstract class HybridBirdsEntityModel<T: HybridBirdsBirdEntity> (private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): Identifier {
        return Identifier.of(HybridBirds.MOD_ID, "geo/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): Identifier {
        return Identifier.of(HybridBirds.MOD_ID, "textures/entity/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier.of(HybridBirds.MOD_ID, "animations/$id.animation.json")
    }

    override fun setCustomAnimations(animatable: T, instanceId: Long, animationState: AnimationState<T>) {
        val head = animationProcessor.getBone("head")

        if (head != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            head.rotX = entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE
            head.rotY = entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE
        }
    }
}