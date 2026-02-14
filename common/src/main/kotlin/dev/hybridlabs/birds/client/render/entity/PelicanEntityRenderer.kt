package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.PelicanEntityModel
import dev.hybridlabs.birds.entity.bird.PelicanEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PelicanEntityRenderer(context: Context) :
    BirdEntityRenderer<PelicanEntity>(context,
        PelicanEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}