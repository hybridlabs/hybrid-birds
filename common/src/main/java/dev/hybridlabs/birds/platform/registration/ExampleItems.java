package dev.hybridlabs.birds.platform.registration;

import dev.hybridlabs.birds.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

/**
 * Example class for item registration
 */
public class ExampleItems {

    /**
     * The provider for items
     */
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, Constants.MOD_ID);

    public static final RegistryObject<Item> EXAMPLE = ITEMS.register("example", () -> new Item(new Item.Properties().fireResistant().stacksTo(12)));

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
