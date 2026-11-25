package dev.hybridlabs.skies.client.model.entity.bird

import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.skies.BaseFlyingAnimalEntityModel
import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.skies.SeagullEntity
import net.minecraft.resources.Identifier

class SeagullEntityModel : BaseFlyingAnimalEntityModel<SeagullEntity>("hybrid_birds", "seagull") {
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/seagull/seagull")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/seagull/seagull.png")
    }

    override fun getAnimationResource(animatable: SeagullEntity): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/seagull/seagull")
    }
}
