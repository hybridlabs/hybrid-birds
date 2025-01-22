package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.entity.bird.ChickEntity
import dev.hybridlabs.birds.entity.bird.PeacockEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class PeacockEntityModel : GeoModel<PeacockEntity>() {
    override fun getModelResource(animatable: PeacockEntity?): Identifier {
        return MODEL_RESOURCE
    }

    override fun getTextureResource(animatable: PeacockEntity?): Identifier {
        return TEXTURE_RESOURCE
    }

    override fun getAnimationResource(animatable: PeacockEntity?): Identifier {
        return ANIMATION_RESOURCE
    }

    companion object {
        const val ID = "peacock"

        val MODEL_RESOURCE = HybridBirdsEntityModel.createModelResource(ID)
        val TEXTURE_RESOURCE = HybridBirdsEntityModel.createTextureResource(ID)
        val ANIMATION_RESOURCE = HybridBirdsEntityModel.createAnimationResource(ID)
    }
}
