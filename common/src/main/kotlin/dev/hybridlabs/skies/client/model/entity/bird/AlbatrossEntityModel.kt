package dev.hybridlabs.skies.client.model.entity.bird

import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.bird.AlbatrossEntity
import dev.hybridlabs.hapi.client.model.entity.flying.BaseFlyingAnimalEntityModel
import net.minecraft.resources.ResourceLocation

class AlbatrossEntityModel : BaseFlyingAnimalEntityModel<AlbatrossEntity>("hybrid_birds", "albatross") {

    override fun getModelResource(animatable: AlbatrossEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/entity/albatross/albatross.geo.json")
    }

    override fun getTextureResource(animatable: AlbatrossEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/albatross/albatross.png")
    }

    override fun getAnimationResource(animatable: AlbatrossEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/entity/albatross/albatross.animation.json")
    }
}