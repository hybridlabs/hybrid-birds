package dev.hybridlabs.skies.client.model.entity.bird

import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.skies.PuffinEntity
import dev.hybridlabs.hapi.client.model.entity.flying.BaseFlyingAnimalEntityModel
import net.minecraft.resources.ResourceLocation

class PuffinEntityModel : BaseFlyingAnimalEntityModel<PuffinEntity>("hybrid_birds", "puffin") {

    override fun getModelResource(animatable: PuffinEntity): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/puffin/puffin.geo.json")
    }

    override fun getTextureResource(animatable: PuffinEntity): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/puffin/puffin.png")
    }

    override fun getAnimationResource(animatable: PuffinEntity): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "animations/entity/puffin/puffin.animation.json")
    }
}