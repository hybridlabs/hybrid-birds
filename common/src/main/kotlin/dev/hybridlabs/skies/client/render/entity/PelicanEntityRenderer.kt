package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.PelicanEntityModel
import dev.hybridlabs.skies.entity.skies.PelicanEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class PelicanEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<PelicanEntity, LivingEntityRenderState>(context,
        PelicanEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}
