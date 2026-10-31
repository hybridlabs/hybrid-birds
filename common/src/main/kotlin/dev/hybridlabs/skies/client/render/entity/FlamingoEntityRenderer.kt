package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.hapi.client.render.entity.skies.BaseBirdEntityRenderer
import dev.hybridlabs.skies.client.model.entity.bird.FlamingoEntityModel
import dev.hybridlabs.skies.entity.skies.FlamingoEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FlamingoEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<FlamingoEntity>(context, FlamingoEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}
