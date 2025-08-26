package dev.hybridlabs.birds;

import dev.hybridlabs.birds.block.HybridBirdsBlocks;
import dev.hybridlabs.birds.effect.HybridBirdsStatusEffects;
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes;
import dev.hybridlabs.birds.entity.SpawnRestrictionRegistry;
import dev.hybridlabs.birds.item.HybridBirdsItemGroups;
import dev.hybridlabs.birds.item.HybridBirdsItems;
import dev.hybridlabs.birds.render.entity.HybridBirdsEntityRenderers;
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;


@Mod(Constants.MOD_ID)
public class HybridBirdsForge {
    public HybridBirdsForge() {
        HybridBirdsCommon.init();
        HybridBirdsBlocks.load();
        HybridBirdsItems.load();
        HybridBirdsItemGroups.load();
        HybridBirdsStatusEffects.load();
        HybridBirdsEntityTypes.load();
        HybridBirdsSoundEvents.load();
        SpawnRestrictionRegistry.load();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> HybridBirdsEntityRenderers::load);
    }
}
