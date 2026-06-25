package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.AlbatrossEntityModel
import dev.hybridlabs.birds.entity.bird.AlbatrossEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class AlbatrossEntityRenderer(context: Context) :
    BirdEntityRenderer<AlbatrossEntity>(context,
        AlbatrossEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}