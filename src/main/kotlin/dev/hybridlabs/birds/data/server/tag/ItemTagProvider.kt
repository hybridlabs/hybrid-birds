package dev.hybridlabs.birds.data.server.tag

import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.tag.HybridBirdsItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Items
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        setOf(
            HybridBirdsItems.DUCK,
            HybridBirdsItems.TURKEY,
            Items.CHICKEN,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridBirdsItemTags.TURDUCKEN_INGREDIENTS).add(item)
        }
    }
}
