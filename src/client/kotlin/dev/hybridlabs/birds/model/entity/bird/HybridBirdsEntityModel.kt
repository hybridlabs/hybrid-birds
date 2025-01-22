package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.HybridBirds
import net.minecraft.util.Identifier

object HybridBirdsEntityModel {
    fun createModelResource(id: String): Identifier {
        return Identifier(HybridBirds.MOD_ID, "geo/$id.geo.json")
    }

    fun createTextureResource(id: String): Identifier {
        return Identifier(HybridBirds.MOD_ID, "textures/entity/$id.png")
    }

    fun createAnimationResource(id: String): Identifier {
        return Identifier(HybridBirds.MOD_ID, "animations/$id.animation.json")
    }
}
