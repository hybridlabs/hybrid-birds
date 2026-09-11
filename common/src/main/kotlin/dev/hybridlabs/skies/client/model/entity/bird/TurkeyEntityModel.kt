package dev.hybridlabs.skies.client.model.entity.bird

import dev.hybridlabs.skies.Constants
import dev.hybridlabs.skies.entity.skies.TurkeyEntity
import dev.hybridlabs.hapi.client.model.entity.flying.BaseFlyingAnimalEntityModel
import net.minecraft.resources.ResourceLocation

class TurkeyEntityModel : BaseFlyingAnimalEntityModel<TurkeyEntity>("hybrid_birds", "turkey") {

    private val BABY_MODEL = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/entity/turkey/baby_turkey.geo.json")
    private val NORMAL_MODEL = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/entity/turkey/turkey.geo.json")
    private val FAT_MODEL = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/entity/turkey/turkey_fat.geo.json")
    private val STUFFED_MODEL = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/entity/turkey/turkey_stuffed.geo.json")

    private val BABY_TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey/baby_turkey.png")
    private val NORMAL_TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey/turkey.png")
    private val FAT_TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey/turkey_fat.png")
    private val STUFFED_TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey/turkey_stuffed.png")

    override fun getModelResource(animatable: TurkeyEntity): ResourceLocation {
        return if (animatable.isBaby) {
            BABY_MODEL
        } else {
            when (animatable.getStuffingLevel()) {
                0 -> NORMAL_MODEL
                1 -> FAT_MODEL
                2 -> STUFFED_MODEL
                else -> NORMAL_MODEL
            }
        }
    }

    override fun getTextureResource(animatable: TurkeyEntity): ResourceLocation {
        return if (animatable.isBaby) {
            BABY_TEXTURE
        } else {
            when (animatable.getStuffingLevel()) {
                0 -> NORMAL_TEXTURE
                1 -> FAT_TEXTURE
                2 -> STUFFED_TEXTURE
                else -> NORMAL_TEXTURE
            }
        }
    }
}