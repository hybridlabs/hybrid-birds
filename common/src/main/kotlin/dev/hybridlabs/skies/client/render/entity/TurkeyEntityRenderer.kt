package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.client.model.entity.bird.TurkeyEntityModel
import dev.hybridlabs.skies.entity.skies.TurkeyEntity
import dev.hybridlabs.hapi.client.render.entity.flying.BaseBirdEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TurkeyEntityRenderer(context: Context) :
    BaseBirdEntityRenderer<TurkeyEntity>(context, TurkeyEntityModel()) {

    init {
        this.shadowRadius = 0.4f
    }
}