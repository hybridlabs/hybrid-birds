package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.RoosterEntityModel
import dev.hybridlabs.skies.entity.skies.RoosterEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class RoosterEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<RoosterEntity, LivingEntityRenderState>(context, RoosterEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}
