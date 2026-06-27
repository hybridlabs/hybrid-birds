package dev.hybridlabs.birds

import dev.hybridlabs.birds.block.HBBlocks
import dev.hybridlabs.birds.effect.HBMobEffects
import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.item.HBItemGroups
import dev.hybridlabs.birds.item.HBItems
import dev.hybridlabs.birds.sound.HBSoundEvents
import dev.hybridlabs.birds.tag.HBBiomeTags
import dev.hybridlabs.birds.tag.HBItemTags
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
object HybridBirdsForge {

    init {
        CommonClass.init()

        HBSoundEvents
        HBEntityTypes

        HBBlocks
        HBItems
        HBItemGroups

        HBBiomeTags
        HBItemTags

        HBMobEffects
    }
}