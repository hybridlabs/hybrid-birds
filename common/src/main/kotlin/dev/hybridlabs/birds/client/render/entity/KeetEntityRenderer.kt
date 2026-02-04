package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.KeetEntityModel
import dev.hybridlabs.birds.entity.bird.KeetEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class KeetEntityRenderer(context: Context) :
    BirdEntityRenderer<KeetEntity>(context, KeetEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}