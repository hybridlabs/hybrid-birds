package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.PeacockEntityModel
import dev.hybridlabs.birds.entity.bird.PeacockEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PeacockEntityRenderer(context: Context) :
    BirdEntityRenderer<PeacockEntity>(context, PeacockEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}