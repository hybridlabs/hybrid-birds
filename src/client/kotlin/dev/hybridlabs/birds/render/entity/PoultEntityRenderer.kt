package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.PoultEntity
import dev.hybridlabs.birds.model.entity.bird.PoultEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class PoultEntityRenderer(context: Context) :
    BirdEntityRenderer<PoultEntity>(context, PoultEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}