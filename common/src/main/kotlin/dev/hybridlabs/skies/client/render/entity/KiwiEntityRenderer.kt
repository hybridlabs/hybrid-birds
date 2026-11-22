package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseRatiteEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.KiwiEntityModel
import dev.hybridlabs.skies.entity.skies.KiwiEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class KiwiEntityRenderer(context: Context) :
    BaseRatiteEntityRenderer<KiwiEntity, LivingEntityRenderState>(context,
        KiwiEntityModel()
    ) {

    init {
        this.shadowRadius = 0.15f
    }
}
