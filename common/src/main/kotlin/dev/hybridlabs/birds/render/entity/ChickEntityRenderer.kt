package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.ChickEntity
import dev.hybridlabs.birds.model.entity.bird.ChickEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ChickEntityRenderer(context: Context) :
    BirdEntityRenderer<ChickEntity>(context, ChickEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}