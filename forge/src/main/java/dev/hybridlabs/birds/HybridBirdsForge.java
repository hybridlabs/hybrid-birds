package dev.hybridlabs.birds;

import dev.hybridlabs.birds.effect.HybridBirdsStatusEffects;
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes;
import dev.hybridlabs.birds.sound.HybridBirdsSoundEvents;
import net.minecraftforge.fml.common.Mod;


@Mod(Constants.MOD_ID)
public class HybridBirdsForge {
    public HybridBirdsForge() {
        CommonClass.init();
        HybridBirdsStatusEffects.load();
        HybridBirdsEntityTypes.load();
        HybridBirdsSoundEvents.load();
    }
}
