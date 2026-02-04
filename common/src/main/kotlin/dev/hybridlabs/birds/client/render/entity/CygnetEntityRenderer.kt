package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.bird.CygnetEntity
import dev.hybridlabs.birds.client.model.entity.bird.CygnetEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CygnetEntityRenderer(context: Context) :
    BirdEntityRenderer<CygnetEntity>(context,
        CygnetEntityModel()
    ) {

    init {
        this.shadowRadius = 0.15f
    }
}