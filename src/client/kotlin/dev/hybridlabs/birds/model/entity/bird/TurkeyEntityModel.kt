package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.entity.bird.TurkeyEntity
import net.minecraft.util.Identifier

class TurkeyEntityModel : HybridBirdsEntityModel<TurkeyEntity>("turkey") {

    private val NORMAL_MODEL = Identifier("hybrid-birds", "geo/turkey.geo.json")
    private val FAT_MODEL = Identifier("hybrid-birds", "geo/rooster.geo.json")
    private val STUFFED_MODEL = Identifier("hybrid-birds", "geo/swan.geo.json")

    override fun getModelResource(animatable: TurkeyEntity): Identifier {
        return when (animatable.getStuffingLevel()) {
            0 -> NORMAL_MODEL
            1 -> FAT_MODEL
            2 -> STUFFED_MODEL
            else -> NORMAL_MODEL
        }
    }
}