package dev.hybridlabs.birds.client.model.entity.bird

import dev.hybridlabs.birds.Constants
import dev.hybridlabs.birds.entity.bird.TurkeyEntity
import net.minecraft.resources.ResourceLocation

class TurkeyEntityModel : HBBirdEntityModel<TurkeyEntity>("turkey") {

    private val BABY_MODEL = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/baby_turkey.geo.json")
    private val NORMAL_MODEL = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/turkey.geo.json")
    private val FAT_MODEL = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/turkey_fat.geo.json")
    private val STUFFED_MODEL = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/turkey_stuffed.geo.json")

    private val BABY_TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/baby_turkey.png")
    private val NORMAL_TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey.png")
    private val FAT_TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey_fat.png")
    private val STUFFED_TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/turkey_stuffed.png")

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