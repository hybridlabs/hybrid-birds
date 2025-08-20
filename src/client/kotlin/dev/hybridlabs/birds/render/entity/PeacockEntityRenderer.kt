package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.PeacockEntity
import dev.hybridlabs.birds.model.entity.bird.PeacockEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class PeacockEntityRenderer(context: Context) :
    BirdEntityRenderer<PeacockEntity>(context, PeacockEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}