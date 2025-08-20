package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.DucklingEntity
import dev.hybridlabs.birds.model.entity.bird.DucklingEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class DucklingEntityRenderer(context: Context) :
    BirdEntityRenderer<DucklingEntity>(context, DucklingEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}