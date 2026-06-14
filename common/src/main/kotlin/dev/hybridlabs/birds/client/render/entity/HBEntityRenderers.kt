@file:Suppress("unused")

package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.platform.ClientServices

object HBEntityRenderers {

    val ROOSTER = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.ROOSTER, ::RoosterEntityRenderer)
    val CHICK = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.CHICK, ::ChickEntityRenderer)
    val TURKEY = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.TURKEY, ::TurkeyEntityRenderer)
    val PEACOCK = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.PEACOCK, ::PeacockEntityRenderer)
    val GUINEA_FOWL = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.GUINEA_FOWL, ::GuineaFowlEntityRenderer)
    val DUCK = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.DUCK, ::DuckEntityRenderer)
    val GOOSE = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.GOOSE, ::GooseEntityRenderer)
    val SWAN = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.SWAN, ::SwanEntityRenderer)
    val JAY = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.JAY, ::JayEntityRenderer)
    val SEAGULL = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.SEAGULL, ::SeagullEntityRenderer)
    val PELICAN = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.PELICAN, ::PelicanEntityRenderer)
    val OSTRICH = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.OSTRICH, ::OstrichEntityRenderer)
    val KIWI = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.KIWI, ::KiwiEntityRenderer)
    val HUMMINGBIRD = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.HUMMINGBIRD, ::HummingbirdEntityRenderer)
    val FLAMINGO = ClientServices.RENDERER.registerEntityRenderer(HBEntityTypes.FLAMINGO, ::FlamingoEntityRenderer)
}
