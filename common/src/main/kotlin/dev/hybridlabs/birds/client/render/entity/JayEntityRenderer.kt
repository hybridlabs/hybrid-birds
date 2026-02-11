package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.bird.JayEntity
import dev.hybridlabs.birds.client.model.entity.bird.JayEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class JayEntityRenderer(context: Context) :
    HybridBirdsParrotEntityRenderer<JayEntity>(context,
        JayEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}