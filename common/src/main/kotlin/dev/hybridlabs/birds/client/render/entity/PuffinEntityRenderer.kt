package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.PuffinEntityModel
import dev.hybridlabs.birds.entity.bird.PuffinEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PuffinEntityRenderer(context: Context) :
    BirdEntityRenderer<PuffinEntity>(context,
        PuffinEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}