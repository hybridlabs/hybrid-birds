package dev.hybridlabs.birds.render.entity.bird

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.model.entity.ChickEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory

class ChickEntityRenderer(context: EntityRendererFactory.Context) : HybridBirdsBirdEntityRenderer<HybridBirdsBirdEntity>(context, ChickEntityModel())