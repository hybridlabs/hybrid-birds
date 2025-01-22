package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.model.entity.bird.ChickEntityModel
import dev.hybridlabs.birds.model.entity.bird.DuckEntityModel
import dev.hybridlabs.birds.model.entity.bird.GooseEntityModel
import dev.hybridlabs.birds.model.entity.bird.RoosterEntityModel
import dev.hybridlabs.birds.model.entity.bird.SwanEntityModel
import dev.hybridlabs.birds.model.entity.bird.TurkeyEntityModel
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import software.bernie.geckolib.renderer.GeoEntityRenderer

object HybridBirdsEntityRenderers {
    val ROOSTER = EntityRendererRegistry.register(HybridBirdsEntityTypes.ROOSTER) { GeoEntityRenderer(it, RoosterEntityModel()) }
    val CHICK = EntityRendererRegistry.register(HybridBirdsEntityTypes.CHICK) { GeoEntityRenderer(it, ChickEntityModel()) }
    val TURKEY = EntityRendererRegistry.register(HybridBirdsEntityTypes.TURKEY) { GeoEntityRenderer(it, TurkeyEntityModel()) }
    val DUCK = EntityRendererRegistry.register(HybridBirdsEntityTypes.DUCK) { GeoEntityRenderer(it, DuckEntityModel()) }
    val GOOSE = EntityRendererRegistry.register(HybridBirdsEntityTypes.GOOSE) { GeoEntityRenderer(it, GooseEntityModel()) }
    val SWAN = EntityRendererRegistry.register(HybridBirdsEntityTypes.SWAN) { GeoEntityRenderer(it, SwanEntityModel()) }
}
