package dev.hybridlabs.skies.client.model.entity.bird

import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.bird.SeagullEntity
import dev.hybridlabs.hapi.client.model.entity.flying.BaseFlyingAnimalEntityModel
import net.minecraft.resources.ResourceLocation

class SeagullEntityModel : BaseFlyingAnimalEntityModel<SeagullEntity>("hybrid_birds", "seagull") {

    override fun getModelResource(animatable: SeagullEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/entity/seagull/seagull.geo.json")
    }

    override fun getTextureResource(animatable: SeagullEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/seagull/seagull.png")
    }

    override fun getAnimationResource(animatable: SeagullEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/entity/seagull/seagull.animation.json")
    }
}