package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.SeagullEntityModel
import dev.hybridlabs.birds.entity.bird.SeagullEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeagullEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<SeagullEntity>(context,
        SeagullEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}