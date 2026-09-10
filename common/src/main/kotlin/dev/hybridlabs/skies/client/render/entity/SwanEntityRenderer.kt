package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.client.model.entity.bird.SwanEntityModel
import dev.hybridlabs.skies.entity.bird.SwanEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SwanEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<SwanEntity>(context, SwanEntityModel()) {

    init {
        this.shadowRadius = 0.5f
    }
}