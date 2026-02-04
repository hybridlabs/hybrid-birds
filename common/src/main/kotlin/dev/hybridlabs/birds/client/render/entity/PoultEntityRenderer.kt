package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.PoultEntityModel
import dev.hybridlabs.birds.entity.bird.PoultEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PoultEntityRenderer(context: Context) :
    BirdEntityRenderer<PoultEntity>(context, PoultEntityModel()) {

    init {
        this.shadowRadius = 0.15f
    }
}