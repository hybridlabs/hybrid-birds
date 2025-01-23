package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.entity.bird.DuckEntity
import dev.hybridlabs.birds.entity.bird.GuineaFowlEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class GuineaFowlEntityModel : GeoModel<GuineaFowlEntity>() {
    override fun getModelResource(animatable: GuineaFowlEntity?): Identifier {
        return MODEL_RESOURCE
    }

    override fun getTextureResource(animatable: GuineaFowlEntity?): Identifier {
        return TEXTURE_RESOURCE
    }

    override fun getAnimationResource(animatable: GuineaFowlEntity?): Identifier {
        return ANIMATION_RESOURCE
    }

    companion object {
        const val ID = "duck"

        val MODEL_RESOURCE = HybridBirdsEntityModel.createModelResource(ID)
        val TEXTURE_RESOURCE = HybridBirdsEntityModel.createTextureResource(ID)
        val ANIMATION_RESOURCE = HybridBirdsEntityModel.createAnimationResource(ID)
    }
}
