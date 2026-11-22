package dev.hybridlabs.skies.client.model.entity.bird

import dev.hybridlabs.hapi.client.model.entity.skies.BaseFlyingAnimalEntityModel
import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.skies.TurkeyEntity
import net.minecraft.resources.Identifier
import software.bernie.geckolib.constant.dataticket.DataTicket
import software.bernie.geckolib.renderer.base.GeoRenderState

class TurkeyEntityModel : BaseFlyingAnimalEntityModel<TurkeyEntity>("hybrid_birds", "turkey") {

    override fun addAdditionalStateData(
        animatable: TurkeyEntity,
        relatedObject: Any?,
        renderState: GeoRenderState,
    ) {
        renderState.addGeckolibData(STUFFING_LEVEL, animatable.getStuffingLevel())
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        if (renderState.getGeckolibData(IS_BABY) == true) {
            return BABY_MODEL
        }

        return when (renderState.getGeckolibData(STUFFING_LEVEL)) {
            1 -> FAT_MODEL
            2 -> STUFFED_MODEL
            else -> NORMAL_MODEL
        }
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        if (renderState.getGeckolibData(IS_BABY) == true) {
            return BABY_TEXTURE
        }

        return when (renderState.getGeckolibData(STUFFING_LEVEL)) {
            1 -> FAT_TEXTURE
            2 -> STUFFED_TEXTURE
            else -> NORMAL_TEXTURE
        }
    }

    companion object {
        private val IS_BABY = dev.hybridlabs.hapi.client.model.HAPIDataTickets.IS_BABY
        private val STUFFING_LEVEL: DataTicket<Int> =
            DataTicket.create("stuffing_level", Int::class.java)

        private val BABY_MODEL = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/turkey/baby_turkey")
        private val NORMAL_MODEL = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/turkey/turkey")
        private val FAT_MODEL = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/turkey/turkey_fat")
        private val STUFFED_MODEL = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "entity/turkey/turkey_stuffed")

        private val BABY_TEXTURE = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey/baby_turkey.png")
        private val NORMAL_TEXTURE = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey/turkey.png")
        private val FAT_TEXTURE = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey/turkey_fat.png")
        private val STUFFED_TEXTURE = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey/turkey_stuffed.png")
    }
}
