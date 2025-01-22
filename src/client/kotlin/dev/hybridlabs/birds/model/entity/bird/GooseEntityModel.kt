package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.entity.bird.GooseEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class GooseEntityModel : GeoModel<GooseEntity>() {
    override fun getModelResource(animatable: GooseEntity?): Identifier {
        return MODEL_RESOURCE
    }

    override fun getTextureResource(animatable: GooseEntity?): Identifier {
        return TEXTURE_RESOURCE
    }

    override fun getAnimationResource(animatable: GooseEntity?): Identifier {
        return ANIMATION_RESOURCE
    }

    companion object {
        const val ID = "goose"

        val MODEL_RESOURCE = HybridBirdsEntityModel.createModelResource(ID)
        val TEXTURE_RESOURCE = HybridBirdsEntityModel.createTextureResource(ID)
        val ANIMATION_RESOURCE = HybridBirdsEntityModel.createAnimationResource(ID)
    }
}
