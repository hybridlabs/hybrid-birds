package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.GoslingEntity
import dev.hybridlabs.birds.model.entity.bird.GoslingEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class GoslingEntityRenderer(context: Context) :
    BirdEntityRenderer<GoslingEntity>(context, GoslingEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}