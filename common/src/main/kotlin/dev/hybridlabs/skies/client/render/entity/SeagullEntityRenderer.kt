package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.client.model.entity.bird.SeagullEntityModel
import dev.hybridlabs.skies.entity.skies.SeagullEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeagullEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<SeagullEntity>(context,
        SeagullEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}