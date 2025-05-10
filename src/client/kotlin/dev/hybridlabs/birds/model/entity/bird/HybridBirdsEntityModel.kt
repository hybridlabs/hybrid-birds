package dev.hybridlabs.birds.model.entity.bird

import dev.hybridlabs.birds.HybridBirds
import dev.hybridlabs.birds.entity.bird.BirdEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

abstract class HybridBirdsEntityModel<T: BirdEntity> (private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): Identifier {
        return Identifier(HybridBirds.MOD_ID, "geo/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): Identifier {
        return Identifier(HybridBirds.MOD_ID, "textures/entity/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier(HybridBirds.MOD_ID, "animations/$id.animation.json")
    }
}