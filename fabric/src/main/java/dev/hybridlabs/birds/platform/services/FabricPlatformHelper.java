package dev.hybridlabs.birds.platform.services;

import dev.hybridlabs.birds.HybridBirdsCommon;
import dev.hybridlabs.birds.platform.registration.RegistryObject;

import dev.hybridlabs.birds.utils.HybridBirdsSpawnGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.Heightmap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class FabricPlatformHelper implements PlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <T extends Mob> Supplier<SpawnEggItem> registerSpawnEggItem(
            @NotNull String name,
            Supplier<EntityType<T>> entityType,
            int backgroundColor,
            int highlightColor) {
        return HybridBirdsCommon.ITEMS.register(
                name,
                () ->
                        new SpawnEggItem(
                                entityType.get(),
                                backgroundColor,
                                highlightColor,
                                new Item.Properties()));
    }

    @Override
    public Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public <T extends Mob> void registerSpawnPlacement(
            RegistryObject<EntityType<T>> entityType,
            SpawnPlacements.Type decoratorType,
            Heightmap.Types heightMapType,
            SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        SpawnPlacements.register(
                entityType.get(), decoratorType, heightMapType, decoratorPredicate);
    }

    @Override
    public <T extends LivingEntity> void registerAttributes(
            @NotNull String id,
            EntityType<T> entityType,
            Callable<AttributeSupplier.Builder> attributeContainer) {
        try {
            FabricDefaultAttributeRegistry.register(entityType, attributeContainer.call());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @Nullable MobCategory getMobCategoryByName(String name) {
        return HybridBirdsSpawnGroup.byName(name);
    }

    public BlockBehaviour.Properties getBlockSettings() {
        return FabricBlockSettings.create();
    }
}
