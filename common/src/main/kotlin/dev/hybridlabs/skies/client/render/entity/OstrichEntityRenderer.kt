package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.client.model.entity.bird.OstrichEntityModel
import dev.hybridlabs.skies.entity.bird.OstrichEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseRatiteEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OstrichEntityRenderer(context: Context) :
    BaseRatiteEntityRenderer<OstrichEntity>(context, OstrichEntityModel()) {

    init {
        this.shadowRadius = 0.5f
    }
}