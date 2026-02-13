package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.HummingbirdEntityModel
import dev.hybridlabs.birds.entity.bird.HummingbirdEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HummingbirdEntityRenderer(context: Context) :
    HybridBirdsParrotEntityRenderer<HummingbirdEntity>(context,
        HummingbirdEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}