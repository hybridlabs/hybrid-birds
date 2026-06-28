package dev.hybridlabs.birds
import dev.hybridlabs.birds.block.HBBlocks
import dev.hybridlabs.birds.client.render.entity.HBEntityRenderers
import dev.hybridlabs.birds.effect.HBMobEffects
import dev.hybridlabs.birds.entity.ForgeSpawnGroupRegistry
import dev.hybridlabs.birds.entity.HBEntityTypes
import dev.hybridlabs.birds.entity.SpawnRestrictionRegistry
import dev.hybridlabs.birds.item.HBItemGroups
import dev.hybridlabs.birds.item.HBItems
import dev.hybridlabs.birds.sound.HBSoundEvents
import dev.hybridlabs.birds.tag.HBBiomeTags
import dev.hybridlabs.birds.tag.HBItemTags
import dev.hybridlabs.birds.utils.HBSpawnGroup
import net.minecraft.world.entity.MobCategory
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Suppress("UnusedExpression")
@Mod(Constants.MOD_ID)
object HybridBirdsForge {
    private val LOGGER = Constants.LOG

    init {
        CommonClass.init()

        ForgeSpawnGroupRegistry.createHybridAquaticSpawnGroups()
        createSpawnGroups()
        HBSoundEvents
        HBEntityTypes

        HBBlocks
        HBItems
        HBItemGroups

        HBBiomeTags
        HBItemTags

        HBMobEffects

        MOD_BUS.addListener(::registerSpawnPlacements)

        runForDist(
            clientTarget = {
                HBEntityRenderers
                MOD_BUS.addListener(HybridBirdsForge::onClientSetup)
            },
            serverTarget = {
                MOD_BUS.addListener(HybridBirdsForge::onServerSetup)
            }
        )
    }

    private fun createSpawnGroups() {
        // Extend the MobCategory enum with our spawn groups
        HBSpawnGroup.entries.toTypedArray().forEach {
            MobCategory.create(
                it.name,
                it.name,
                it.spawnCap,
                it.peaceful,
                it.rare,
                it.immediateDespawnRange
            )
        }
    }

    private fun registerSpawnPlacements(event: SpawnPlacementRegisterEvent) {
        SpawnRestrictionRegistry.registerSpawnRestrictions()
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.info("Initializing client...")
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.info("Server starting...")
    }
}
