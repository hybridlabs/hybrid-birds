package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.render.entity.bird.DuckEntityRenderer
import dev.hybridlabs.birds.render.entity.bird.SeagullEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object HybridBirdsEntityRenderers {
    val SEAGULL = EntityRendererRegistry.register(HybridBirdsEntityTypes.SEAGULL, ::SeagullEntityRenderer)
    val DUCK = EntityRendererRegistry.register(HybridBirdsEntityTypes.DUCK, ::DuckEntityRenderer)
}