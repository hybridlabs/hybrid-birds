package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.DuckEntity
import dev.hybridlabs.birds.model.entity.bird.DuckEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class DuckEntityRenderer(context: Context) :
    BirdEntityRenderer<DuckEntity>(context, DuckEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}