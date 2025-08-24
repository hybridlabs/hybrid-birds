package dev.hybridlabs.birds.platform.services;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FabricBlockHelper implements BlockHelper{
    @Override
    public BlockBehaviour.Properties getBlockSettings() {
        return FabricBlockSettings.create();
    }
}
