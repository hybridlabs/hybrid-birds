package dev.hybridlabs.birds.data.server.tag

import dev.hybridlabs.birds.item.HBItems
import dev.hybridlabs.birds.tag.HBItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        setOf(
            HBItems.DUCK.get(),
            HBItems.TURKEY.get(),
            Items.CHICKEN,
        ).forEach { item ->
            getOrCreateTagBuilder(HBItemTags.TURDUCKEN_INGREDIENTS).add(item)
        }

        setOf(
            HBItems.DUCK_EGG.get(),
            HBItems.GOOSE_EGG.get(),
            HBItems.SWAN_EGG.get(),
            HBItems.TURKEY_EGG.get(),
            HBItems.PEACOCK_EGG.get(),
            HBItems.GUINEA_FOWL_EGG.get(),
            Items.EGG,
        ).forEach { item ->
            getOrCreateTagBuilder(HBItemTags.EGGS  ).add(item)
        }
    }
}
