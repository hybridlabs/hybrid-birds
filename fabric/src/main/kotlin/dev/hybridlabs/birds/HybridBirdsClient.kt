package dev.hybridlabs.birds

import dev.hybridlabs.birds.client.render.entity.HybridBirdsEntityRenderers
import net.fabricmc.api.ClientModInitializer

object HybridBirdsClient : ClientModInitializer {
	override fun onInitializeClient() {
		registerEntityRenderers()
	}

	private fun registerEntityRenderers() {
        HybridBirdsEntityRenderers
	}
}
