package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.OstrichEntityModel
import dev.hybridlabs.birds.entity.bird.OstrichEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OstrichEntityRenderer(context: Context) : HBRatiteEntityRenderer<OstrichEntity>(context, OstrichEntityModel()) {

    init {
        this.shadowRadius = 0.5f
    }
}