package dev.hybridlabs.birds.render.entity.bird

import dev.hybridlabs.birds.entity.bird.HybridBirdsBirdEntity
import dev.hybridlabs.birds.model.entity.DuckEntityModel
import dev.hybridlabs.birds.model.entity.HummingbirdEntityModel
import dev.hybridlabs.birds.model.entity.SeagullEntityModel
import net.minecraft.client.render.entity.EntityRendererFactory

class HummingbirdEntityRenderer(context: EntityRendererFactory.Context) : HybridBirdsBirdEntityRenderer<HybridBirdsBirdEntity>(context, HummingbirdEntityModel())