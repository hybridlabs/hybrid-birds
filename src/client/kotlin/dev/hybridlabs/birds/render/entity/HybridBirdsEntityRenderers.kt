package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.entity.bird.GuineaFowlEntity
import dev.hybridlabs.birds.model.entity.bird.*
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import software.bernie.geckolib.renderer.GeoEntityRenderer

object HybridBirdsEntityRenderers {
    val ROOSTER = EntityRendererRegistry.register(HybridBirdsEntityTypes.ROOSTER) { GeoEntityRenderer(it, RoosterEntityModel()) }
    val CHICK = EntityRendererRegistry.register(HybridBirdsEntityTypes.CHICK) { GeoEntityRenderer(it, ChickEntityModel()) }
    val TURKEY = EntityRendererRegistry.register(HybridBirdsEntityTypes.TURKEY) { GeoEntityRenderer(it, TurkeyEntityModel()) }
    val PEACOCK = EntityRendererRegistry.register(HybridBirdsEntityTypes.PEACOCK) { GeoEntityRenderer(it, PeacockEntityModel()) }
    val GUINEA_FOWL = EntityRendererRegistry.register(HybridBirdsEntityTypes.GUINEA_FOWL) { GeoEntityRenderer(it, GuineaFowlEntityModel()) }
    val DUCK = EntityRendererRegistry.register(HybridBirdsEntityTypes.DUCK) { GeoEntityRenderer(it, DuckEntityModel()) }
    val GOOSE = EntityRendererRegistry.register(HybridBirdsEntityTypes.GOOSE) { GeoEntityRenderer(it, GooseEntityModel()) }
    val SWAN = EntityRendererRegistry.register(HybridBirdsEntityTypes.SWAN) { GeoEntityRenderer(it, SwanEntityModel()) }
}
