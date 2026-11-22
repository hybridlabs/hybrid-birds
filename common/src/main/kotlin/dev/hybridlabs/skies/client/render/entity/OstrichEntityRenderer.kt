package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseRatiteEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.OstrichEntityModel
import dev.hybridlabs.skies.entity.skies.OstrichEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class OstrichEntityRenderer(context: Context) :
    BaseRatiteEntityRenderer<OstrichEntity, LivingEntityRenderState>(context,
        OstrichEntityModel()
    ) {

    init {
        this.shadowRadius = 0.5f
    }
}
