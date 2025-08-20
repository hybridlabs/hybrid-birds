package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.entity.bird.TurkeyEntity
import net.minecraft.util.Identifier

class TurkeyEntityModel : HybridBirdsEntityModel<TurkeyEntity>("turkey") {

    private val NORMAL_MODEL = Identifier.of("hybrid-birds", "geo/turkey.geo.json")
    private val FAT_MODEL = Identifier.of("hybrid-birds", "geo/turkey_fat.geo.json")
    private val STUFFED_MODEL = Identifier.of("hybrid-birds", "geo/turkey_stuffed.geo.json")

    private val NORMAL_TEXTURE = Identifier.of("hybrid-birds", "textures/entity/turkey.png")
    private val FAT_TEXTURE = Identifier.of("hybrid-birds", "textures/entity/turkey_fat.png")
    private val STUFFED_TEXTURE = Identifier.of("hybrid-birds", "textures/entity/turkey_stuffed.png")

    override fun getModelResource(animatable: TurkeyEntity): Identifier {
        return when (animatable.getStuffingLevel()) {
            0 -> NORMAL_MODEL
            1 -> FAT_MODEL
            2 -> STUFFED_MODEL
            else -> NORMAL_MODEL
        }
    }

    override fun getTextureResource(animatable: TurkeyEntity): Identifier {
        return when (animatable.getStuffingLevel()) {
            0 -> NORMAL_TEXTURE
            1 -> FAT_TEXTURE
            2 -> STUFFED_TEXTURE
            else -> NORMAL_TEXTURE
        }
    }
}