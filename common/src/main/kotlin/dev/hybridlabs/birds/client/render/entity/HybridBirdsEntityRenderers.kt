package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.platform.ClientServices

object HybridBirdsEntityRenderers {

    val ROOSTER = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.ROOSTER, ::RoosterEntityRenderer)
    val CHICK = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.CHICK, ::ChickEntityRenderer)
    val TURKEY = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.TURKEY, ::TurkeyEntityRenderer)
    val PEACOCK = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.PEACOCK, ::PeacockEntityRenderer)
    val GUINEA_FOWL = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.GUINEA_FOWL, ::GuineaFowlEntityRenderer)
    val DUCK = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.DUCK, ::DuckEntityRenderer)
    val GOOSE = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.GOOSE, ::GooseEntityRenderer)
    val SWAN = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.SWAN, ::SwanEntityRenderer)
    val JAY = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.JAY, ::JayEntityRenderer)
    val OSTRICH = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.OSTRICH, ::OstrichEntityRenderer)
    val KIWI = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.KIWI, ::KiwiEntityRenderer)
    val HUMMINGBIRD = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.HUMMINGBIRD, ::HummingbirdEntityRenderer)
    val FLAMINGO = ClientServices.RENDERER.registerEntityRenderer(HybridBirdsEntityTypes.FLAMINGO, ::FlamingoEntityRenderer)
}
