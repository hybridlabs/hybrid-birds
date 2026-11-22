package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.GuineaFowlEntityModel
import dev.hybridlabs.skies.entity.skies.GuineaFowlEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class GuineaFowlEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<GuineaFowlEntity, LivingEntityRenderState>(context,
        GuineaFowlEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}
