package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.KiwiEntityModel
import dev.hybridlabs.birds.entity.bird.KiwiEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class KiwiEntityRenderer(context: Context) : HBRatiteEntityRenderer<KiwiEntity>(context, KiwiEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}