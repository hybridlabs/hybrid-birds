package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.PelicanEntity
import dev.hybridlabs.hapi.client.model.entity.flying.BaseFlyingAnimalEntityModel
import net.minecraft.resources.ResourceLocation

class PelicanEntityModel : BaseFlyingAnimalEntityModel<PelicanEntity>("hybrid_birds", "pelican") {

    override fun getModelResource(animatable: PelicanEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/pelican.geo.json")
    }

    override fun getTextureResource(animatable: PelicanEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/pelican.png")
    }

    override fun getAnimationResource(animatable: PelicanEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/pelican.animation.json")
    }
}