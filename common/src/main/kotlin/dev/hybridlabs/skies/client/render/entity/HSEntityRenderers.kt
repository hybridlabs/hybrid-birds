@file:Suppress("unused")

package dev.hybridlabs.skies.client.render.entity

import dev.hybridlabs.skies.entity.HSEntityTypes
import dev.hybridlabs.skies.platform.ClientServices

object HSEntityRenderers {

    val ROOSTER = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.ROOSTER, ::RoosterEntityRenderer)
    val TURKEY = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.TURKEY, ::TurkeyEntityRenderer)
    val PEACOCK = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.PEACOCK, ::PeacockEntityRenderer)
    val GUINEA_FOWL = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.GUINEA_FOWL, ::GuineaFowlEntityRenderer)
    val DUCK = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.DUCK, ::DuckEntityRenderer)
    val GOOSE = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.GOOSE, ::GooseEntityRenderer)
    val SWAN = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.SWAN, ::SwanEntityRenderer)
    //val JAY = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.JAY, ::JayEntityRenderer)
    val PUFFIN = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.PUFFIN, ::PuffinEntityRenderer)
    val SEAGULL = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.SEAGULL, ::SeagullEntityRenderer)
    val ALBATROSS = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.ALBATROSS, ::AlbatrossEntityRenderer)
    val PELICAN = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.PELICAN, ::PelicanEntityRenderer)
    //val OSTRICH = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.OSTRICH, ::OstrichEntityRenderer)
    //val KIWI = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.KIWI, ::KiwiEntityRenderer)
    //val HUMMINGBIRD = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.HUMMINGBIRD, ::HummingBaseBirdEntityRenderer)
    //val FLAMINGO = ClientServices.RENDERER.registerEntityRenderer(HSEntityTypes.FLAMINGO, ::FlamingoEntityRenderer)
}
