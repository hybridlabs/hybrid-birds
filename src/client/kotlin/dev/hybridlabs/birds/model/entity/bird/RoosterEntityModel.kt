package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.entity.bird.RoosterEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class RoosterEntityModel : GeoModel<RoosterEntity>() {
    override fun getModelResource(animatable: RoosterEntity?): Identifier {
        return MODEL_RESOURCE
    }

    override fun getTextureResource(animatable: RoosterEntity?): Identifier {
        return TEXTURE_RESOURCE
    }

    override fun getAnimationResource(animatable: RoosterEntity?): Identifier {
        return ANIMATION_RESOURCE
    }

    companion object {
        const val ID = "rooster"

        val MODEL_RESOURCE = HybridBirdsEntityModel.createModelResource(ID)
        val TEXTURE_RESOURCE = HybridBirdsEntityModel.createTextureResource(ID)
        val ANIMATION_RESOURCE = HybridBirdsEntityModel.createAnimationResource(ID)
    }
}
