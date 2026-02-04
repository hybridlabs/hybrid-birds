package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer

open class BirdEntityRenderer<T : HybridBirdsBirdEntity>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>
) : GeoEntityRenderer<T>(context, model) {
    init {
        this.shadowRadius = 0.3f
    }
}
