package dev.hybridlabs.birds.render.entity.bird

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.model.entity.RoosterEntityModel
import dev.hybridlabs.birds.model.entity.TurkeyEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory

class TurkeyEntityRenderer(context: EntityRendererFactory.Context) :
    HybridBirdsBirdEntityRenderer<HybridBirdsBirdEntity>(context, TurkeyEntityModel())