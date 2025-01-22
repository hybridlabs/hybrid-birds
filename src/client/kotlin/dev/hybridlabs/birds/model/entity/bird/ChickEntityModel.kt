package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.entity.bird.ChickEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class ChickEntityModel : GeoModel<ChickEntity>() {
    override fun getModelResource(animatable: ChickEntity?): Identifier {
        return MODEL_RESOURCE
    }

    override fun getTextureResource(animatable: ChickEntity?): Identifier {
        return TEXTURE_RESOURCE
    }

    override fun getAnimationResource(animatable: ChickEntity?): Identifier {
        return ANIMATION_RESOURCE
    }

    companion object {
        const val ID = "chick"

        val MODEL_RESOURCE = HybridBirdsEntityModel.createModelResource(ID)
        val TEXTURE_RESOURCE = HybridBirdsEntityModel.createTextureResource(ID)
        val ANIMATION_RESOURCE = HybridBirdsEntityModel.createAnimationResource(ID)
    }
}
