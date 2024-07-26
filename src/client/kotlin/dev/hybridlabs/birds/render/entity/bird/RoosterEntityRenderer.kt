package dev.hybridlabs.birds.render.entity.bird

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.model.entity.RoosterEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory

class RoosterEntityRenderer(context: EntityRendererFactory.Context) : HybridBirdsBirdEntityRenderer<HybridBirdsBirdEntity>(context, RoosterEntityModel())