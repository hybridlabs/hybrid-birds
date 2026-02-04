package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.SwanEntityModel
import dev.hybridlabs.birds.entity.bird.SwanEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SwanEntityRenderer(context: Context) :
    BirdEntityRenderer<SwanEntity>(context, SwanEntityModel()) {

    init {
        this.shadowRadius = 0.5f
    }
}