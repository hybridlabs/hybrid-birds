package dev.hybridlabs.birds.render.entity.bird

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.model.entity.SwanEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory

class SwanEntityRenderer(context: EntityRendererFactory.Context) :
    HybridBirdsBirdEntityRenderer<HybridBirdsBirdEntity>(context, SwanEntityModel())