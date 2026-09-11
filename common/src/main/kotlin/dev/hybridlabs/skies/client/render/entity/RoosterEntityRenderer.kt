package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.client.model.entity.bird.RoosterEntityModel
import dev.hybridlabs.skies.entity.skies.RoosterEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class RoosterEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<RoosterEntity>(context, RoosterEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}