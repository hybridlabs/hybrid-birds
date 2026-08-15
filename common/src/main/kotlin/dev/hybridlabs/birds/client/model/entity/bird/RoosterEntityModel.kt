package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.RoosterEntity
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