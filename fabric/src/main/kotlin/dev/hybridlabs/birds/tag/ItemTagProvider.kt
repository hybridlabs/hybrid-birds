package dev.hybridlabs.birds.tag

import dev.hybridlabs.birds.item.HybridBirdsItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider?) {
        setOf(
            HybridBirdsItems.DUCK.get(),
            HybridBirdsItems.TURKEY.get(),
            Items.CHICKEN,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridBirdsItemTags.TURDUCKEN_INGREDIENTS).add(item)
        }

        setOf(
            HybridBirdsItems.DUCK_EGG.get(),
            HybridBirdsItems.GOOSE_EGG.get(),
            HybridBirdsItems.SWAN_EGG.get(),
            HybridBirdsItems.TURKEY_EGG.get(),
            HybridBirdsItems.PEACOCK_EGG.get(),
            HybridBirdsItems.GUINEA_FOWL_EGG.get(),
            Items.EGG,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridBirdsItemTags.EGGS  ).add(item)
        }
    }
}
