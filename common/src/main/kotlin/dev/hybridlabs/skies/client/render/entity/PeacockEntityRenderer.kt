package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.PeacockEntityModel
import dev.hybridlabs.skies.entity.skies.PeacockEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PeacockEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<PeacockEntity>(context, PeacockEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}
