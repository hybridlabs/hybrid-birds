package dev.hybridlabs.birds;

import dev.hybridlabs.birds.block.HybridBirdsBlocks;
import dev.hybridlabs.birds.effect.HybridBirdsStatusEffects;
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes;
import dev.hybridlabs.birds.entity.SpawnRestrictionRegistry;
import dev.hybridlabs.birds.item.HybridBirdsItems;
import dev.hybridlabs.birds.render.entity.HybridBirdsEntityRenderers;
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;


@Mod(Constants.MOD_ID)
public class HybridBirdsForge {
    public HybridBirdsForge() {
        CommonClass.init();
        HybridBirdsBlocks.load();
        HybridBirdsItems.load();
        HybridBirdsStatusEffects.load();
        HybridBirdsEntityTypes.load();
        HybridBirdsSoundEvents.load();
        SpawnRestrictionRegistry.load();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> HybridBirdsEntityRenderers::load);
    }
}
