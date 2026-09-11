package dev.hybridlabs.skies.client.model.entity.bird

import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.skies.RoosterEntity
import dev.hybridlabs.hapi.client.model.entity.flying.BaseFlyingAnimalEntityModel
import net.minecraft.resources.ResourceLocation

class RoosterEntityModel : BaseFlyingAnimalEntityModel<RoosterEntity>("hybrid_birds", "rooster") {

    override fun getModelResource(animatable: RoosterEntity): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/entity/rooster/rooster.geo.json")
    }

    override fun getTextureResource(animatable: RoosterEntity): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/rooster/rooster.png")
    }

    override fun getAnimationResource(animatable: RoosterEntity): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "animations/entity/rooster/rooster.animation.json")
    }
}