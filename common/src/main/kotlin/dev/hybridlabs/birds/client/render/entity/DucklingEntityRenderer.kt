package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.bird.DucklingEntity
import dev.hybridlabs.birds.client.model.entity.bird.DucklingEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DucklingEntityRenderer(context: Context) :
    BirdEntityRenderer<DucklingEntity>(context,
        DucklingEntityModel()
    ) {

    init {
        this.shadowRadius = 0.15f
    }
}