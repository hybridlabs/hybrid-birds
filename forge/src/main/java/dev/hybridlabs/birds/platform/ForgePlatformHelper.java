package dev.hybridlabs.birds.platform;

import dev.hybridlabs.birds.CommonClass;
import dev.hybridlabs.birds.platform.registration.RegistryObject;
import dev.hybridlabs.birds.platform.services.PlatformHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import org.jetbrains.annotations.NotNull;

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
        return CommonClass.ITEMS.register( name, ()->new ForgeSpawnEggItem(entityType,backgroundColor,highlightColor,new Item.Properties()));
    }
}