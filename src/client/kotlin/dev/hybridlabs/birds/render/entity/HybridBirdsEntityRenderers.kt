package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object HybridBirdsEntityRenderers {
    val ROOSTER = EntityRendererRegistry.register(HybridBirdsEntityTypes.ROOSTER, ::RoosterEntityRenderer)
    val CHICK = EntityRendererRegistry.register(HybridBirdsEntityTypes.CHICK, ::ChickEntityRenderer)
    val DUCKLING = EntityRendererRegistry.register(HybridBirdsEntityTypes.DUCKLING, ::DucklingEntityRenderer)
    val GOSLING = EntityRendererRegistry.register(HybridBirdsEntityTypes.GOSLING, ::GoslingEntityRenderer)
    val CYGNET = EntityRendererRegistry.register(HybridBirdsEntityTypes.CYGNET, ::CygnetEntityRenderer)
    val POULT = EntityRendererRegistry.register(HybridBirdsEntityTypes.POULT, ::PoultEntityRenderer)
    val PEACHICK = EntityRendererRegistry.register(HybridBirdsEntityTypes.PEACHICK, ::PeachickEntityRenderer)
    val KEET = EntityRendererRegistry.register(HybridBirdsEntityTypes.KEET, ::KeetEntityRenderer)
    val TURKEY = EntityRendererRegistry.register(HybridBirdsEntityTypes.TURKEY, ::TurkeyEntityRenderer)
    val PEACOCK = EntityRendererRegistry.register(HybridBirdsEntityTypes.PEACOCK, ::PeacockEntityRenderer)
    val GUINEA_FOWL = EntityRendererRegistry.register(HybridBirdsEntityTypes.GUINEA_FOWL, ::GuineaFowlEntityRenderer)
    val DUCK = EntityRendererRegistry.register(HybridBirdsEntityTypes.DUCK, ::DuckEntityRenderer)
    val GOOSE = EntityRendererRegistry.register(HybridBirdsEntityTypes.GOOSE, ::GooseEntityRenderer)
    val SWAN = EntityRendererRegistry.register(HybridBirdsEntityTypes.SWAN, ::SwanEntityRenderer)
}
