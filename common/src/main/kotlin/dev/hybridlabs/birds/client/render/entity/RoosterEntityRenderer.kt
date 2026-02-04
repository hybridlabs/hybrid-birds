package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.RoosterEntityModel
import dev.hybridlabs.birds.entity.bird.RoosterEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class RoosterEntityRenderer(context: Context) :
    BirdEntityRenderer<RoosterEntity>(context, RoosterEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}