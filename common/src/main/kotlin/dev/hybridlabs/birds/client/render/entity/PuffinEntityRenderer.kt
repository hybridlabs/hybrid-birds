package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.PuffinEntityModel
import dev.hybridlabs.birds.entity.bird.PuffinEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PuffinEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<PuffinEntity>(context,
        PuffinEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}