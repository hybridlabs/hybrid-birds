package dev.hybridlabs.birds;

import dev.hybridlabs.birds.platform.Services;
import dev.hybridlabs.birds.platform.registration.RegistrationProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class CommonClass {

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.

    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(BuiltInRegistries.BLOCK, Constants.MOD_ID);
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, Constants.MOD_ID);
    public static final RegistrationProvider<SoundEvent> SOUND_EVENTS = RegistrationProvider.get(BuiltInRegistries.SOUND_EVENT, Constants.MOD_ID);
    public static final RegistrationProvider<MobEffect> MOB_EFFECTS = RegistrationProvider.get(BuiltInRegistries.MOB_EFFECT, Constants.MOD_ID);
    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(BuiltInRegistries.ENTITY_TYPE, Constants.MOD_ID);


    public static void init() {

        Constants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        Constants.LOG.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));

        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.
        if (Services.PLATFORM.isModLoaded("examplemod")) {

            Constants.LOG.info("Hello to examplemod");
        }

    }
}