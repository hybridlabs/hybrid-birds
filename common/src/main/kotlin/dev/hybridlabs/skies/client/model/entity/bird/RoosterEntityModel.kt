package dev.hybridlabs.skies.client.model.entity.bird

import dev.hybridlabs.hapi.client.model.entity.skies.BaseFlyingAnimalEntityModel
import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.skies.RoosterEntity

class RoosterEntityModel : BaseFlyingAnimalEntityModel<RoosterEntity>("hybrid_birds", "rooster") {
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/rooster/rooster")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/rooster/rooster.png")
    }

    override fun getAnimationResource(animatable: RoosterEntity): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/rooster/rooster")
    }
}
