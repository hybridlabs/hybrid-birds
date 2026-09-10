package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.client.model.entity.bird.PelicanEntityModel
import dev.hybridlabs.skies.entity.bird.PelicanEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PelicanEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<PelicanEntity>(context,
        PelicanEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}