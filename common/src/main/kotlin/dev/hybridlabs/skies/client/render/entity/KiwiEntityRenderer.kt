package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.client.model.entity.bird.KiwiEntityModel
import dev.hybridlabs.skies.entity.skies.KiwiEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseRatiteEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class KiwiEntityRenderer(context: Context) :
    BaseRatiteEntityRenderer<KiwiEntity>(context,
        KiwiEntityModel()
    ) {

    init {
        this.shadowRadius = 0.15f
    }
}