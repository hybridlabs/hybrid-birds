package dev.hybridlabs.skies
import dev.hybridlabs.skies.block.HSBlocks
import dev.hybridlabs.skies.client.render.entity.HSEntityRenderers
import dev.hybridlabs.skies.effect.HSMobEffects
import dev.hybridlabs.skies.entity.ForgeSpawnGroupRegistry
import dev.hybridlabs.skies.entity.HSEntityTypes
import dev.hybridlabs.skies.entity.SpawnRestrictionRegistry
import dev.hybridlabs.skies.item.HSItemGroups
import dev.hybridlabs.skies.item.HSItems
import dev.hybridlabs.skies.sound.HSSoundEvents
import dev.hybridlabs.skies.tag.HSBiomeTags
import dev.hybridlabs.skies.tag.HSItemTags
import dev.hybridlabs.skies.utils.HBSpawnGroup
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
object HybridSkiesForge {
    private val LOGGER = Constants.LOG

    init {
        CommonClass.init()

        ForgeSpawnGroupRegistry.createHybridAquaticSpawnGroups()
        createSpawnGroups()
        HSSoundEvents
        HSEntityTypes

        HSBlocks
        HSItems
        HSItemGroups

        HSBiomeTags
        HSItemTags

        HSMobEffects

        MOD_BUS.addListener(::registerSpawnPlacements)

        runForDist(
            clientTarget = {
                HSEntityRenderers
                MOD_BUS.addListener(HybridSkiesForge::onClientSetup)
            },
            serverTarget = {
                MOD_BUS.addListener(HybridSkiesForge::onServerSetup)
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
