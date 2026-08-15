package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.PeacockEntityModel
import dev.hybridlabs.birds.entity.bird.PeacockEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PeacockEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<PeacockEntity>(context, PeacockEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}