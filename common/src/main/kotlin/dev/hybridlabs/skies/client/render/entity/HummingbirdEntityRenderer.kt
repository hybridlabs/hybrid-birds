package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseParrotEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.HummingbirdEntityModel
import dev.hybridlabs.skies.entity.skies.HummingbirdEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class HummingbirdEntityRenderer(context: Context) :
    BaseParrotEntityRenderer<HummingbirdEntity, LivingEntityRenderState>(context,
        HummingbirdEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}
