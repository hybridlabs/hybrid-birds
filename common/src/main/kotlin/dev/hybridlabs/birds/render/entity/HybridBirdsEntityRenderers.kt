package dev.hybridlabs.birds.render.entity

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes
import dev.hybridlabs.birds.platform.ClientServices

object HybridBirdsEntityRenderers {
    @JvmStatic
    fun load() { }
    val ROOSTER = ClientServices.RENDERER.register(HybridBirdsEntityTypes.ROOSTER, ::RoosterEntityRenderer)
    val CHICK = ClientServices.RENDERER.register(HybridBirdsEntityTypes.CHICK, ::ChickEntityRenderer)
    val DUCKLING = ClientServices.RENDERER.register(HybridBirdsEntityTypes.DUCKLING, ::DucklingEntityRenderer)
    val GOSLING = ClientServices.RENDERER.register(HybridBirdsEntityTypes.GOSLING, ::GoslingEntityRenderer)
    val CYGNET = ClientServices.RENDERER.register(HybridBirdsEntityTypes.CYGNET, ::CygnetEntityRenderer)
    val POULT = ClientServices.RENDERER.register(HybridBirdsEntityTypes.POULT, ::PoultEntityRenderer)
    val PEACHICK = ClientServices.RENDERER.register(HybridBirdsEntityTypes.PEACHICK, ::PeachickEntityRenderer)
    val KEET = ClientServices.RENDERER.register(HybridBirdsEntityTypes.KEET, ::KeetEntityRenderer)
    val TURKEY = ClientServices.RENDERER.register(HybridBirdsEntityTypes.TURKEY, ::TurkeyEntityRenderer)
    val PEACOCK = ClientServices.RENDERER.register(HybridBirdsEntityTypes.PEACOCK, ::PeacockEntityRenderer)
    val GUINEA_FOWL = ClientServices.RENDERER.register(HybridBirdsEntityTypes.GUINEA_FOWL, ::GuineaFowlEntityRenderer)
    val DUCK = ClientServices.RENDERER.register(HybridBirdsEntityTypes.DUCK, ::DuckEntityRenderer)
    val GOOSE = ClientServices.RENDERER.register(HybridBirdsEntityTypes.GOOSE, ::GooseEntityRenderer)
    val SWAN = ClientServices.RENDERER.register(HybridBirdsEntityTypes.SWAN, ::SwanEntityRenderer)
}
