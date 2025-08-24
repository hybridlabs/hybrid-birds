package dev.hybridlabs.birds.platform;


import dev.hybridlabs.birds.platform.services.BlockHelper;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ForgeBlockHelper implements BlockHelper {
    @Override
    public BlockBehaviour.Properties getBlockSettings() {
        return BlockBehaviour.Properties.of();
    }
}
