package dev.hybridlabs.skies.client.model.entity.bird

import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.skies.BaseFlyingAnimalEntityModel
import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.skies.PuffinEntity
import net.minecraft.resources.Identifier

class PuffinEntityModel : BaseFlyingAnimalEntityModel<PuffinEntity>("hybrid_birds", "puffin") {
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/puffin/puffin")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/puffin/puffin.png")
    }

    override fun getAnimationResource(animatable: PuffinEntity): Identifier {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/puffin/puffin")
    }
}
