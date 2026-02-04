package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.bird.GoslingEntity
import dev.hybridlabs.birds.client.model.entity.bird.GoslingEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GoslingEntityRenderer(context: Context) :
    BirdEntityRenderer<GoslingEntity>(context,
        GoslingEntityModel()
    ) {

    init {
        this.shadowRadius = 0.15f
    }
}