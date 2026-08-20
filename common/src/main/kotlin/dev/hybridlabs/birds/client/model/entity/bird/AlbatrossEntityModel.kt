package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.AlbatrossEntity
import dev.hybridlabs.hapi.client.model.entity.flying.BaseFlyingAnimalEntityModel
import net.minecraft.resources.ResourceLocation

class AlbatrossEntityModel : BaseFlyingAnimalEntityModel<AlbatrossEntity>("hybrid_birds", "albatross") {

    override fun getModelResource(animatable: AlbatrossEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/albatross.geo.json")
    }

    override fun getTextureResource(animatable: AlbatrossEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/albatross.png")
    }

    override fun getAnimationResource(animatable: AlbatrossEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/albatross.animation.json")
    }
}