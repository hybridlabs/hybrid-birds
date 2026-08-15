package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.bird.JayEntity
import dev.hybridlabs.birds.client.model.entity.bird.JayEntityModel
import dev.hybridlabs.hapi.client.render.entity.flying.BaseParrotEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class JayEntityRenderer(context: Context) :
    BaseParrotEntityRenderer<JayEntity>(context,
        JayEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}