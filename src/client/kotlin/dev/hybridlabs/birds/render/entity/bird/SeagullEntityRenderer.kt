package dev.hybridlabs.birds.render.entity.bird

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.model.entity.SeagullEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory

class SeagullEntityRenderer(context: EntityRendererFactory.Context) : HybridBirdsBirdEntityRenderer<HybridBirdsBirdEntity>(context, SeagullEntityModel())