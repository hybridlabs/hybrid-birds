package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.SwanEntity
import dev.hybridlabs.birds.model.entity.bird.SwanEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class SwanEntityRenderer(context: Context) :
    BirdEntityRenderer<SwanEntity>(context, SwanEntityModel()) {

    init {
        this.shadowRadius = 0.5f
    }
}