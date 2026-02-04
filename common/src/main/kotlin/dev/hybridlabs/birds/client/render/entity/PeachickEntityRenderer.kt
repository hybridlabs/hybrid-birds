package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.PeachickEntityModel
import dev.hybridlabs.birds.entity.bird.PeachickEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PeachickEntityRenderer(context: Context) :
    BirdEntityRenderer<PeachickEntity>(context, PeachickEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}