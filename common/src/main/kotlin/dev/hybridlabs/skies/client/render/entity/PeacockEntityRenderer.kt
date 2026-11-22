package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.PeacockEntityModel
import dev.hybridlabs.skies.entity.skies.PeacockEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class PeacockEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<PeacockEntity, LivingEntityRenderState>(context, PeacockEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}
