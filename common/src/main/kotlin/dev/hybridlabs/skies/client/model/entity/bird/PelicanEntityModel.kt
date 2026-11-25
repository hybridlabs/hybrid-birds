package dev.hybridlabs.skies.client.model.entity.bird

import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.skies.BaseFlyingAnimalEntityModel
import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.skies.PelicanEntity
import net.minecraft.resources.Identifier

class PelicanEntityModel : BaseFlyingAnimalEntityModel<PelicanEntity>("hybrid_birds", "pelican") {
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/pelican/pelican")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/pelican/pelican.png")
    }

    override fun getAnimationResource(animatable: PelicanEntity): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/pelican/pelican")
    }
}
