package dev.hybridlabs.birds.data.server.tag

import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.tag.HybridBirdsItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider?) {
        setOf(
            HybridBirdsItems.DUCK,
            HybridBirdsItems.TURKEY,
            Items.CHICKEN,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridBirdsItemTags.TURDUCKEN_INGREDIENTS).add(item)
        }

        setOf(
            HybridBirdsItems.DUCK_EGG,
            HybridBirdsItems.GOOSE_EGG,
            HybridBirdsItems.SWAN_EGG,
            HybridBirdsItems.TURKEY_EGG,
            HybridBirdsItems.PEACOCK_EGG,
            HybridBirdsItems.GUINEA_FOWL_EGG,
            Items.EGG,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridBirdsItemTags.EGGS  ).add(item)
        }
    }
}
