package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.KiwiEntityModel
import dev.hybridlabs.birds.client.model.entity.bird.OstrichEntityModel
import dev.hybridlabs.birds.entity.bird.KiwiEntity
import dev.hybridlabs.birds.entity.bird.OstrichEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class KiwiEntityRenderer(context: Context) : HybridBirdsRatiteEntityRenderer<KiwiEntity>(context, KiwiEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}