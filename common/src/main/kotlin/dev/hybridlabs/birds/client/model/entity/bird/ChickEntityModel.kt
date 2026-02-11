package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.ChickEntity
import net.minecraft.resources.ResourceLocation

class ChickEntityModel : HybridBirdsEntityModel<ChickEntity>("chick") {

    override fun getModelResource(animatable: ChickEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/chick.geo.json")
    }

    override fun getTextureResource(animatable: ChickEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/chick.png")
    }

    override fun getAnimationResource(animatable: ChickEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/chick.animation.json")
    }
}