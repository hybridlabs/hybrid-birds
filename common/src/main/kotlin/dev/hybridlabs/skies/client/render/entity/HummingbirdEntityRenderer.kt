package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.client.model.entity.bird.HummingbirdEntityModel
import dev.hybridlabs.skies.entity.skies.HummingbirdEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseParrotEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HummingbirdEntityRenderer(context: Context) :
    BaseParrotEntityRenderer<HummingbirdEntity>(context,
        HummingbirdEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}