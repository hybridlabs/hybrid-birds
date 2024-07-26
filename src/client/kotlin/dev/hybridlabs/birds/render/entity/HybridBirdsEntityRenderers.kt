package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.render.entity.bird.RoosterEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object HybridBirdsEntityRenderers {
    val ROOSTER = EntityRendererRegistry.register(HybridBirdsEntityTypes.ROOSTER, ::RoosterEntityRenderer)
}