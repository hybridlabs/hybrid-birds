package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.SwanEntityModel
import dev.hybridlabs.skies.entity.skies.SwanEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SwanEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<SwanEntity, LivingEntityRenderState>(context, SwanEntityModel()) {

    init {
        this.shadowRadius = 0.5f
    }
}
