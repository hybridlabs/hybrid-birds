package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.bird.ChickEntity
import dev.hybridlabs.birds.client.model.entity.bird.ChickEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ChickEntityRenderer(context: Context) :
    BirdEntityRenderer<ChickEntity>(context,
        ChickEntityModel()
    ) {

    init {
        this.shadowRadius = 0.15f
    }
}