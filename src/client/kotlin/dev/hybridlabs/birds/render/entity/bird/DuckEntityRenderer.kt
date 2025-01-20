package dev.hybridlabs.birds.render.entity.bird

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.model.entity.DuckEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory

class DuckEntityRenderer(context: EntityRendererFactory.Context) :
    HybridBirdsBirdEntityRenderer<HybridBirdsBirdEntity>(context, DuckEntityModel())