package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.render.entity.bird.*
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object HybridBirdsEntityRenderers {
    val ROOSTER = EntityRendererRegistry.register(HybridBirdsEntityTypes.ROOSTER, ::RoosterEntityRenderer)
    val CHICK = EntityRendererRegistry.register(HybridBirdsEntityTypes.CHICK, ::ChickEntityRenderer)
    val TURKEY = EntityRendererRegistry.register(HybridBirdsEntityTypes.TURKEY, ::TurkeyEntityRenderer)
    val DUCK = EntityRendererRegistry.register(HybridBirdsEntityTypes.DUCK, ::DuckEntityRenderer)
    val SWAN = EntityRendererRegistry.register(HybridBirdsEntityTypes.SWAN, ::SwanEntityRenderer)
}