package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer

open class BirdEntityRenderer<T : HybridBirdsBirdEntity>(
    context: EntityRendererFactory.Context,
    model: GeoModel<T>
) : GeoEntityRenderer<T>(context, model) {
    init {
        this.shadowRadius = 0.3f
    }
}
