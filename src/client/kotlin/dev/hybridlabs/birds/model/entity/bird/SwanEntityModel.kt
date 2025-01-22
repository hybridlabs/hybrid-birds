package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.entity.bird.SwanEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class SwanEntityModel : GeoModel<SwanEntity>() {
    override fun getModelResource(animatable: SwanEntity?): Identifier {
        return MODEL_RESOURCE
    }

    override fun getTextureResource(animatable: SwanEntity?): Identifier {
        return TEXTURE_RESOURCE
    }

    override fun getAnimationResource(animatable: SwanEntity?): Identifier {
        return ANIMATION_RESOURCE
    }

    companion object {
        const val ID = "swan"

        val MODEL_RESOURCE = HybridBirdsEntityModel.createModelResource(ID)
        val TEXTURE_RESOURCE = HybridBirdsEntityModel.createTextureResource(ID)
        val ANIMATION_RESOURCE = HybridBirdsEntityModel.createAnimationResource(ID)
    }
}
