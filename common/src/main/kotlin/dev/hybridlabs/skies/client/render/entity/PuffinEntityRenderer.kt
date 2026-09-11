package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.client.model.entity.bird.PuffinEntityModel
import dev.hybridlabs.skies.entity.skies.PuffinEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PuffinEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<PuffinEntity>(context,
        PuffinEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}