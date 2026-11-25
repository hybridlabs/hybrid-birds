package dev.hybridlabs.skies.client.model.entity.bird

import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.skies.BaseFlyingAnimalEntityModel
import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.skies.AlbatrossEntity
import net.minecraft.resources.Identifier

class AlbatrossEntityModel : BaseFlyingAnimalEntityModel<AlbatrossEntity>("hybrid_birds", "albatross") {
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/albatross/albatross")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/albatross/albatross.png")
    }

    override fun getAnimationResource(animatable: AlbatrossEntity): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/albatross/albatross")
    }
}
