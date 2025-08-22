package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.DuckEntity
import dev.hybridlabs.birds.model.entity.bird.DuckEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DuckEntityRenderer(context: Context) :
    BirdEntityRenderer<DuckEntity>(context, DuckEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}