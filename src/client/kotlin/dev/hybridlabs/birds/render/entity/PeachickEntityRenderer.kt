package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.PeachickEntity
import dev.hybridlabs.birds.model.entity.bird.PeachickEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class PeachickEntityRenderer(context: Context) :
    BirdEntityRenderer<PeachickEntity>(context, PeachickEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}