package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.client.model.entity.bird.AlbatrossEntityModel
import dev.hybridlabs.birds.entity.bird.AlbatrossEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class AlbatrossEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<AlbatrossEntity>(context,
        AlbatrossEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}