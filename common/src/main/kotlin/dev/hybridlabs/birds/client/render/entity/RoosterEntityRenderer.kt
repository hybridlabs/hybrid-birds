package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.RoosterEntityModel
import dev.hybridlabs.birds.entity.bird.RoosterEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class RoosterEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<RoosterEntity>(context, RoosterEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}