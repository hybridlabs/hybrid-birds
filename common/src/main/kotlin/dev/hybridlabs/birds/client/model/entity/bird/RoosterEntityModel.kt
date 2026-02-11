package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.RoosterEntity
import net.minecraft.resources.ResourceLocation

class RoosterEntityModel : HybridBirdsEntityModel<RoosterEntity>("rooster") {

    override fun getModelResource(animatable: RoosterEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "geo/rooster.geo.json")
    }

    override fun getTextureResource(animatable: RoosterEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "textures/entity/rooster.png")
    }

    override fun getAnimationResource(animatable: RoosterEntity): ResourceLocation {
        return ResourceLocation(Constants.MOD_ID, "animations/rooster.animation.json")
    }
}