package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.entity.skies.JayEntity
import dev.hybridlabs.skies.client.model.entity.bird.JayEntityModel
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