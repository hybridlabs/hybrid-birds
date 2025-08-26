package dev.hybridlabs.birds.platform;

import dev.hybridlabs.birds.Constants;
import dev.hybridlabs.birds.HybridBirdsCommon;
import dev.hybridlabs.birds.platform.registration.RegistryObject;
import dev.hybridlabs.birds.platform.services.PlatformHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class ForgePlatformHelper implements PlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public <T extends Mob> Supplier<SpawnEggItem> registerSpawnEggItem(@NotNull String name, Supplier<EntityType<T>> entityType, int backgroundColor, int highlightColor) {
        return HybridBirdsCommon.ITEMS.register( name, ()->new ForgeSpawnEggItem(entityType,backgroundColor,highlightColor,new Item.Properties()));
    }

    @Override
    public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public <T extends Mob> void registerSpawnPlacement(RegistryObject<EntityType<T>> entityType, SpawnPlacements.Type decoratorType
            , Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        final ModContainer cont = ModList.get().getModContainerById(Constants.MOD_ID).orElseThrow();
        if (cont instanceof FMLModContainer fmlModContainer) {
            var handler = new SpawnPlacementRegistrationHandler<T>(entityType, decoratorType, heightMapType,
                    decoratorPredicate);
            fmlModContainer.getEventBus().addListener(handler::handleEvent);
        }
    }

    private record SpawnPlacementRegistrationHandler<T extends LivingEntity>(RegistryObject<EntityType<T>> type,
                                                                             SpawnPlacements.Type decoratorType,
                                                                             Heightmap.Types heightMapType,
                                                                             SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        private void handleEvent(SpawnPlacementRegisterEvent event) {
            event.register(type.get(), decoratorType, heightMapType, decoratorPredicate,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
        }
    }

    @Override
    public <T extends LivingEntity> void registerAttributes(@NotNull String id, EntityType<T> entityType,
                                                            Callable<AttributeSupplier.Builder> attributeContainer) {
        final ModContainer cont = ModList.get().getModContainerById(Constants.MOD_ID).orElseThrow();
        if (cont instanceof FMLModContainer fmlModContainer) {
            var handler = new AttributeRegistrationHandler(id, entityType, attributeContainer);
            fmlModContainer.getEventBus().addListener(handler::handleEvent);
        }
    }

    private record  AttributeRegistrationHandler(String id,
                                                 EntityType<? extends LivingEntity> type,
                                                 Callable<AttributeSupplier.Builder> supplier) {
        private void handleEvent(EntityAttributeCreationEvent event){
            try {
                event.put(type,supplier.call().build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public BlockBehaviour.Properties getBlockSettings() {
        return BlockBehaviour.Properties.of();
    }
}