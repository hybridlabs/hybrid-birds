package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.AlbatrossEntityModel
import dev.hybridlabs.skies.entity.skies.AlbatrossEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class AlbatrossEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<AlbatrossEntity, LivingEntityRenderState>(context,
        AlbatrossEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}
