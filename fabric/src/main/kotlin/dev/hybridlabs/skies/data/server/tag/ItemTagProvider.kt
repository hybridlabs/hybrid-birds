package dev.hybridlabs.skies.data.server.tag

import dev.hybridlabs.skies.item.HSItems
import dev.hybridlabs.skies.tag.HBItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        setOf(
            HSItems.DUCK.get(),
            HSItems.TURKEY.get(),
            Items.CHICKEN,
        ).forEach { item ->
            getOrCreateTagBuilder(HBItemTags.TURDUCKEN_INGREDIENTS).add(item)
        }

        setOf(
            HSItems.DUCK_EGG.get(),
            HSItems.GOOSE_EGG.get(),
            HSItems.SWAN_EGG.get(),
            HSItems.TURKEY_EGG.get(),
            HSItems.PEACOCK_EGG.get(),
            HSItems.GUINEA_FOWL_EGG.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HBItemTags.EGGS).add(item)
        }
    }
}
