package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.RoosterEntity
import dev.hybridlabs.birds.model.entity.bird.RoosterEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class RoosterEntityRenderer(context: Context) :
    BirdEntityRenderer<RoosterEntity>(context, RoosterEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}