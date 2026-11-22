package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseParrotEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.JayEntityModel
import dev.hybridlabs.skies.entity.skies.JayEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class JayEntityRenderer(context: Context) :
    BaseParrotEntityRenderer<JayEntity, LivingEntityRenderState>(context,
        JayEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}
