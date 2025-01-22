package dev.hybridlabs.birds.render.entity.bird

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer

open class HybridBirdsBirdEntityRenderer<T : HybridBirdsBirdEntity>(
    context: EntityRendererFactory.Context,
    model: GeoModel<T>,
) : GeoEntityRenderer<T>(context, model) {

    override fun render(
        entity: T,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack,
        bufferSource: VertexConsumerProvider,
        packedLight: Int
    ) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}
