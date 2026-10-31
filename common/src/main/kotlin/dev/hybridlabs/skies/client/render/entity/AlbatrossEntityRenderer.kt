package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.AlbatrossEntityModel
import dev.hybridlabs.skies.entity.skies.AlbatrossEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class AlbatrossEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<AlbatrossEntity>(context,
        AlbatrossEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}
