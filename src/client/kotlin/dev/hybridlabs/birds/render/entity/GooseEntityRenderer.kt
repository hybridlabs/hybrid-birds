package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.GooseEntity
import dev.hybridlabs.birds.model.entity.bird.GooseEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class GooseEntityRenderer(context: Context) :
    BirdEntityRenderer<GooseEntity>(context, GooseEntityModel()) {

    init {
        this.shadowRadius = 0.4f
    }
}