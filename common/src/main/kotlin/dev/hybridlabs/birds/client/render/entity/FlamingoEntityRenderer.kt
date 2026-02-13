package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.FlamingoEntityModel
import dev.hybridlabs.birds.entity.bird.FlamingoEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FlamingoEntityRenderer(context: Context) :
    BirdEntityRenderer<FlamingoEntity>(context, FlamingoEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}