package dev.hybridlabs.birds.render.entity.bird

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.model.entity.DuckEntityModel
import dev.hybridlabs.birds.model.entity.GooseEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory

class GooseEntityRenderer(context: EntityRendererFactory.Context) :
    HybridBirdsBirdEntityRenderer<HybridBirdsBirdEntity>(context, GooseEntityModel())