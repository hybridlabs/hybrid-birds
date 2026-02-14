package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.SeagullEntity
import net.minecraft.resources.ResourceLocation

class SeagullEntityModel : HBBirdEntityModel<SeagullEntity>("seagull") {

    override fun getModelResource(animatable: SeagullEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/seagull.geo.json")
    }

    override fun getTextureResource(animatable: SeagullEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/seagull.png")
    }

    override fun getAnimationResource(animatable: SeagullEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/seagull.animation.json")
    }
}