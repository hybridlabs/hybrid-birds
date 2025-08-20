package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.GuineaFowlEntity
import dev.hybridlabs.birds.model.entity.bird.GuineaFowlEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class GuineaFowlEntityRenderer(context: Context) :
    BirdEntityRenderer<GuineaFowlEntity>(context, GuineaFowlEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}