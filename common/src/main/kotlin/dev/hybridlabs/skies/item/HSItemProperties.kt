package dev.hybridlabs.skies.item

import dev.hybridlabs.skies.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item

/**
 * Item ids are no longer assigned by the registry, they have to be set on [Item.Properties] before
 * the item is constructed. Registration goes through [HSItems.register], which exposes the id being
 * registered here so item factories can build their properties without threading it through every
 * call site.
 */
object HSItemProperties {
    private val currentId = ThreadLocal<ResourceKey<Item>?>()

    fun <T> withId(id: String, factory: () -> T): T {
        val previous = currentId.get()
        currentId.set(ResourceKey.create(Registries.ITEM, CommonClass.locate(id)))
        try {
            return factory()
        } finally {
            currentId.set(previous)
        }
    }

    fun props(): Item.Properties {
        val id = checkNotNull(currentId.get()) { "Item properties must be created during item registration" }
        return Item.Properties().setId(id)
    }
}
