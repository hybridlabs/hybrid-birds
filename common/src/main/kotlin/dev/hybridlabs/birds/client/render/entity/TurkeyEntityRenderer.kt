package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.TurkeyEntityModel
import dev.hybridlabs.birds.entity.bird.TurkeyEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TurkeyEntityRenderer(context: Context) :
    BirdEntityRenderer<TurkeyEntity>(context, TurkeyEntityModel()) {

    init {
        this.shadowRadius = 0.4f
    }
}