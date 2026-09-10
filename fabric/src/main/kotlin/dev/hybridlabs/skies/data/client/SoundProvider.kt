package dev.hybridlabs.skies.data.client

import dev.hybridlabs.skies.CommonClass
import dev.hybridlabs.skies.data.builder.FabricSoundsProvider
import dev.hybridlabs.skies.data.builder.SoundTypeBuilder
import dev.hybridlabs.skies.platform.registration.RegistryObject
import dev.hybridlabs.skies.sound.HSSoundEvents
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import java.util.concurrent.CompletableFuture

class SoundProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>
): FabricSoundsProvider(output, registriesFuture) {

    override fun configure(exporter: SoundExporter) {
        mapOf(
            
            HSSoundEvents.DUCK_AMBIENT to CommonClass.locate("entity/duck_ambient"),
            HSSoundEvents.DUCK_HURT to CommonClass.locate("entity/duck_hurt"),
            HSSoundEvents.DUCK_DIE to CommonClass.locate("entity/duck_die"),
            
            HSSoundEvents.GOOSE_AMBIENT to CommonClass.locate("entity/goose_ambient"),
            HSSoundEvents.GOOSE_HURT to CommonClass.locate("entity/goose_hurt"),
            HSSoundEvents.GOOSE_DIE to CommonClass.locate("entity/goose_die"),

            HSSoundEvents.SWAN_AMBIENT to CommonClass.locate("entity/swan_ambient"),
            HSSoundEvents.SWAN_HURT to CommonClass.locate("entity/swan_hurt"),
            HSSoundEvents.SWAN_DIE to CommonClass.locate("entity/swan_die"),

            HSSoundEvents.TURKEY_AMBIENT to CommonClass.locate("entity/turkey_ambient"),
            HSSoundEvents.TURKEY_HURT to CommonClass.locate("entity/turkey_hurt"),
            HSSoundEvents.TURKEY_DIE to CommonClass.locate("entity/turkey_die"),

            HSSoundEvents.PEACOCK_AMBIENT to CommonClass.locate("entity/peacock_ambient"),
            HSSoundEvents.PEACOCK_HURT to CommonClass.locate("entity/peacock_hurt"),
            HSSoundEvents.PEACOCK_DIE to CommonClass.locate("entity/peacock_die"),

            HSSoundEvents.CHICK_AMBIENT to CommonClass.locate("entity/chick_ambient"),
            HSSoundEvents.CHICK_HURT to CommonClass.locate("entity/chick_hurt"),
            HSSoundEvents.CHICK_DIE to CommonClass.locate("entity/chick_die"),

            HSSoundEvents.ROOSTER_CALL to CommonClass.locate("entity/rooster_call"),
            HSSoundEvents.ROOSTER_AMBIENT to CommonClass.locate("entity/rooster_ambient"),
            HSSoundEvents.ROOSTER_HURT to CommonClass.locate("entity/rooster_hurt"),
            HSSoundEvents.ROOSTER_DIE to CommonClass.locate("entity/rooster_die"),

            HSSoundEvents.PUFFIN_AMBIENT to CommonClass.locate("entity/puffin_ambient"),
            HSSoundEvents.PUFFIN_HURT to CommonClass.locate("entity/puffin_hurt"),
            HSSoundEvents.PUFFIN_DIE to CommonClass.locate("entity/puffin_die"),

            HSSoundEvents.SEAGULL_AMBIENT to CommonClass.locate("entity/seagull_ambient"),
            HSSoundEvents.SEAGULL_HURT to CommonClass.locate("entity/seagull_hurt"),
            HSSoundEvents.SEAGULL_DIE to CommonClass.locate("entity/seagull_die"),

            HSSoundEvents.PELICAN_AMBIENT to CommonClass.locate("entity/pelican_ambient"),
            HSSoundEvents.PELICAN_HURT to CommonClass.locate("entity/pelican_hurt"),
            HSSoundEvents.PELICAN_DIE to CommonClass.locate("entity/pelican_die"),

            HSSoundEvents.ALBATROSS_AMBIENT to CommonClass.locate("entity/albatross_ambient"),
            HSSoundEvents.ALBATROSS_HURT to CommonClass.locate("entity/albatross_hurt"),
            HSSoundEvents.ALBATROSS_DIE to CommonClass.locate("entity/albatross_die"),

            HSSoundEvents.OSTRICH_AMBIENT to CommonClass.locate("entity/ostrich_ambient"),
            HSSoundEvents.OSTRICH_HURT to CommonClass.locate("entity/ostrich_hurt"),
            
            ).forEach { (soundEvent, soundPath) ->
            exporter.add(soundEvent.get(), SoundTypeBuilder.of(soundEvent.get())
                .subtitle("subtitles.${soundEvent.get().location.namespace}.${soundEvent.get().location.path}")
                .sound(when (soundPath) {
                        is SoundEvent -> SoundTypeBuilder.RegistrationBuilder.ofEvent(soundPath)
                        is ResourceLocation -> SoundTypeBuilder.RegistrationBuilder.ofFile(soundPath)
                        is Holder<*> -> SoundTypeBuilder.RegistrationBuilder.ofEvent(soundPath.value() as SoundEvent)
                        is RegistryObject<*> -> SoundTypeBuilder.RegistrationBuilder.ofEvent(soundPath.get() as SoundEvent)
                        else -> SoundTypeBuilder.RegistrationBuilder.ofEvent(SoundEvents.EMPTY)
                    }
                )
            )
        }
    }

    override fun getName(): String {
        return "Hybrid Birds sound events"
    }
}