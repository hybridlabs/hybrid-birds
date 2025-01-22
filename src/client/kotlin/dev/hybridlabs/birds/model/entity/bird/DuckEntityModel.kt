package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.entity.bird.DuckEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class DuckEntityModel : GeoModel<DuckEntity>() {
    override fun getModelResource(animatable: DuckEntity?): Identifier {
        return MODEL_RESOURCE
    }

    override fun getTextureResource(animatable: DuckEntity?): Identifier {
        return TEXTURE_RESOURCE
    }

    override fun getAnimationResource(animatable: DuckEntity?): Identifier {
        return ANIMATION_RESOURCE
    }

    companion object {
        const val ID = "duck"

        val MODEL_RESOURCE = HybridBirdsEntityModel.createModelResource(ID)
        val TEXTURE_RESOURCE = HybridBirdsEntityModel.createTextureResource(ID)
        val ANIMATION_RESOURCE = HybridBirdsEntityModel.createAnimationResource(ID)
    }
}
