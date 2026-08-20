package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.PuffinEntity
import dev.hybridlabs.hapi.client.model.entity.flying.BaseFlyingAnimalEntityModel
import net.minecraft.resources.ResourceLocation

class PuffinEntityModel : BaseFlyingAnimalEntityModel<PuffinEntity>("hybrid_birds", "puffin") {

    override fun getModelResource(animatable: PuffinEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/puffin.geo.json")
    }

    override fun getTextureResource(animatable: PuffinEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/puffin.png")
    }

    override fun getAnimationResource(animatable: PuffinEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/puffin.animation.json")
    }
}