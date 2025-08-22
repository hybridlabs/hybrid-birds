package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.KeetEntity
import dev.hybridlabs.birds.model.entity.bird.KeetEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class KeetEntityRenderer(context: Context) :
    BirdEntityRenderer<KeetEntity>(context, KeetEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}