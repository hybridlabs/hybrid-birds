package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.entity.bird.TurkeyEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class TurkeyEntityModel : GeoModel<TurkeyEntity>() {
    override fun getModelResource(animatable: TurkeyEntity?): Identifier {
        return MODEL_RESOURCE
    }

    override fun getTextureResource(animatable: TurkeyEntity?): Identifier {
        return TEXTURE_RESOURCE
    }

    override fun getAnimationResource(animatable: TurkeyEntity?): Identifier {
        return ANIMATION_RESOURCE
    }

    companion object {
        const val ID = "turkey"

        val MODEL_RESOURCE = HybridBirdsEntityModel.createModelResource(ID)
        val TEXTURE_RESOURCE = HybridBirdsEntityModel.createTextureResource(ID)
        val ANIMATION_RESOURCE = HybridBirdsEntityModel.createAnimationResource(ID)
    }
}
