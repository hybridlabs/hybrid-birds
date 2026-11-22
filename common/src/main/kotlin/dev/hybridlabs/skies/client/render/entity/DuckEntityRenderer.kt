package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.DuckEntityModel
import dev.hybridlabs.skies.entity.skies.DuckEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class DuckEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<DuckEntity, LivingEntityRenderState>(context,
        DuckEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}
