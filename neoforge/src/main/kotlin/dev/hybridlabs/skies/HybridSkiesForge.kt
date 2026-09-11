package dev.hybridlabs.skies

import dev.hybridlabs.skies.block.HSBlocks
import dev.hybridlabs.skies.effect.HSMobEffects
import dev.hybridlabs.skies.entity.HSEntityTypes
import dev.hybridlabs.skies.forge.HybridSkiesModBusEvents
import dev.hybridlabs.skies.item.HSItemGroups
import dev.hybridlabs.skies.item.HSItems
import dev.hybridlabs.skies.sound.HSSoundEvents
import dev.hybridlabs.skies.tag.HSBiomeTags
import dev.hybridlabs.skies.tag.HSItemTags
import net.neoforged.fml.common.Mod

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in neoforge.mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Suppress("UnusedExpression")
@Mod(Constants.MOD_ID)
object HybridSkiesForge {

    init {
        CommonClass.init()

        HSSoundEvents
        HSEntityTypes

        HSBlocks
        HSItems
        HSItemGroups

        HSBiomeTags
        HSItemTags

        HSMobEffects

        HybridSkiesModBusEvents
    }
}