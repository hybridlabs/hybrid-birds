package dev.hybridlabs.birds.client.render.entity

import dev.hybridlabs.birds.entity.bird.BlueJayEntity
import dev.hybridlabs.birds.entity.bird.GuineaFowlEntity
import dev.hybridlabs.birds.client.model.entity.bird.BlueJayEntityModel
import dev.hybridlabs.birds.client.model.entity.bird.GuineaFowlEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BlueJayEntityRenderer(context: Context) :
    BirdEntityRenderer<BlueJayEntity>(context,
        BlueJayEntityModel()
    ) {

    init {
        this.shadowRadius = 0.3f
    }
}