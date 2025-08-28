package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.TurkeyEntity
import dev.hybridlabs.birds.model.entity.bird.TurkeyEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TurkeyEntityRenderer(context: Context) :
    BirdEntityRenderer<TurkeyEntity>(context, TurkeyEntityModel()) {

    init {
        this.shadowRadius = 0.4f
    }
}