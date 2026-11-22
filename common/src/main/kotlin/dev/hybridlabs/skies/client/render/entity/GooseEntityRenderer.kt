package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.GooseEntityModel
import dev.hybridlabs.skies.entity.skies.GooseEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class GooseEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<GooseEntity, LivingEntityRenderState>(context,
        GooseEntityModel()
    ) {

    init {
        this.shadowRadius = 0.4f
    }
}
