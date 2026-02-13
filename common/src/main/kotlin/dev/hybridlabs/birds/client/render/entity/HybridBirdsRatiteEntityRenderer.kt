package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.bird.HBRatiteEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer

open class HybridBirdsRatiteEntityRenderer<T : HBRatiteEntity>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>
) : GeoEntityRenderer<T>(context, model) {
    init {
        this.shadowRadius = 0.3f
    }
}
