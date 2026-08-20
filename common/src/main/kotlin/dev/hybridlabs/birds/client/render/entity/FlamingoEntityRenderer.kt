package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.FlamingoEntityModel
import dev.hybridlabs.birds.entity.bird.FlamingoEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FlamingoEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<FlamingoEntity>(context, FlamingoEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}