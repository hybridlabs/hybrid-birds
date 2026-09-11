package dev.hybridlabs.skies

import dev.hybridlabs.skies.client.render.entity.HBEntityRenderers
import net.fabricmc.api.ClientModInitializer

object HybridSkiesClient : ClientModInitializer {
	override fun onInitializeClient() {
		registerEntityRenderers()
	}

	private fun registerEntityRenderers() {
        HBEntityRenderers
	}
}
