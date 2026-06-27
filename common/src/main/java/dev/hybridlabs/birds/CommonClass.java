package dev.hybridlabs.birds;

import static dev.hybridlabs.birds.Constants.MOD_ID;
import static dev.hybridlabs.birds.Constants.MOD_NAME;

import dev.hybridlabs.birds.platform.Services;
import dev.hybridlabs.birds.platform.registration.RegistrationProvider;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.nio.file.Path;

// This class is part of the common project meaning it is shared between all supported loaders. Code
//  written here can only  import and access the vanilla codebase, libraries used by vanilla, and
// optionally third party  libraries that provide  common compatible binaries. This means common
// code can not directly use loader specific concepts  such as Forge events however it will be
// compatible with all supported mod loaders.
public class CommonClass {

    // Create some static RegistrationProviders for reuse elsewhere.
    public static final RegistrationProvider<Block> BLOCKS =
            RegistrationProvider.get(BuiltInRegistries.BLOCK, MOD_ID);
    public static final RegistrationProvider<Item> ITEMS =
            RegistrationProvider.get(BuiltInRegistries.ITEM, MOD_ID);
    public static final RegistrationProvider<SoundEvent> SOUND_EVENTS =
            RegistrationProvider.get(BuiltInRegistries.SOUND_EVENT, MOD_ID);
    public static final RegistrationProvider<MobEffect> MOB_EFFECTS =
            RegistrationProvider.get(BuiltInRegistries.MOB_EFFECT, MOD_ID);
    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES =
            RegistrationProvider.get(BuiltInRegistries.ENTITY_TYPE, MOD_ID);
    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS =
            RegistrationProvider.get(BuiltInRegistries.CREATIVE_MODE_TAB, MOD_ID);

    public static final Path CONFIG_FILE = Services.PLATFORM.getConfigDir().resolve(MOD_ID + ".json");

    public static ResourceLocation locate(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void init() {
        if (Services.PLATFORM.isModLoaded(MOD_ID)) {
            Constants.LOGGER.info("{} loaded.", MOD_NAME);
        }
    }
}
