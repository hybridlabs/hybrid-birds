package dev.hybridlabs.skies.client.model.entity.bird

import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.bird.PelicanEntity
import dev.hybridlabs.hapi.client.model.entity.flying.BaseFlyingAnimalEntityModel
import net.minecraft.resources.ResourceLocation

class PelicanEntityModel : BaseFlyingAnimalEntityModel<PelicanEntity>("hybrid_birds", "pelican") {

    override fun getModelResource(animatable: PelicanEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/entity/pelican/pelican.geo.json")
    }

    override fun getTextureResource(animatable: PelicanEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/pelican/pelican.png")
    }

    override fun getAnimationResource(animatable: PelicanEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/entity/pelican/pelican.animation.json")
    }
}