package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.entity.bird.GooseEntity
import dev.hybridlabs.skies.client.model.entity.bird.GooseEntityModel
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GooseEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<GooseEntity>(context,
        GooseEntityModel()
    ) {

    init {
        this.shadowRadius = 0.4f
    }
}