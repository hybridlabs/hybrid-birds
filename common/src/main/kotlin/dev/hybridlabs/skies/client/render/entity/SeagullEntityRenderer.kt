package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.SeagullEntityModel
import dev.hybridlabs.skies.entity.skies.SeagullEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SeagullEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<SeagullEntity, LivingEntityRenderState>(context,
        SeagullEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}
