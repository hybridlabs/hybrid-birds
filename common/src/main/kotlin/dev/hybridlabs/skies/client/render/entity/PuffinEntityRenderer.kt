package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.PuffinEntityModel
import dev.hybridlabs.skies.entity.skies.PuffinEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class PuffinEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<PuffinEntity, LivingEntityRenderState>(context,
        PuffinEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}
