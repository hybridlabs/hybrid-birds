package dev.hybridlabs.birds.data.client

import dev.hybridlabs.birds.CommonClass
import dev.hybridlabs.birds.data.builder.FabricSoundsProvider
import dev.hybridlabs.birds.data.builder.SoundTypeBuilder
import dev.hybridlabs.birds.platform.registration.RegistryObject
import dev.hybridlabs.birds.sound.HBSoundEvents
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
            
            HBSoundEvents.DUCK_AMBIENT to CommonClass.locate("entity/duck_ambient"),
            HBSoundEvents.DUCK_HURT to CommonClass.locate("entity/duck_hurt"),
            HBSoundEvents.DUCK_DIE to CommonClass.locate("entity/duck_die"),
            
            HBSoundEvents.GOOSE_AMBIENT to CommonClass.locate("entity/goose_ambient"),
            HBSoundEvents.GOOSE_HURT to CommonClass.locate("entity/goose_hurt"),
            HBSoundEvents.GOOSE_DIE to CommonClass.locate("entity/goose_die"),

            HBSoundEvents.SWAN_AMBIENT to CommonClass.locate("entity/swan_ambient"),
            HBSoundEvents.SWAN_HURT to CommonClass.locate("entity/swan_hurt"),
            HBSoundEvents.SWAN_DIE to CommonClass.locate("entity/swan_die"),

            HBSoundEvents.TURKEY_AMBIENT to CommonClass.locate("entity/turkey_ambient"),
            HBSoundEvents.TURKEY_HURT to CommonClass.locate("entity/turkey_hurt"),
            HBSoundEvents.TURKEY_DIE to CommonClass.locate("entity/turkey_die"),

            HBSoundEvents.PEACOCK_AMBIENT to CommonClass.locate("entity/peacock_ambient"),
            HBSoundEvents.PEACOCK_HURT to CommonClass.locate("entity/peacock_hurt"),
            HBSoundEvents.PEACOCK_DIE to CommonClass.locate("entity/peacock_die"),

            HBSoundEvents.CHICK_AMBIENT to CommonClass.locate("entity/chick_ambient"),
            HBSoundEvents.CHICK_HURT to CommonClass.locate("entity/chick_hurt"),
            HBSoundEvents.CHICK_DIE to CommonClass.locate("entity/chick_die"),

            HBSoundEvents.ROOSTER_CALL to CommonClass.locate("entity/rooster_call"),
            HBSoundEvents.ROOSTER_AMBIENT to CommonClass.locate("entity/rooster_ambient"),
            HBSoundEvents.ROOSTER_HURT to CommonClass.locate("entity/rooster_hurt"),
            HBSoundEvents.ROOSTER_DIE to CommonClass.locate("entity/rooster_die"),

            HBSoundEvents.SEAGULL_AMBIENT to CommonClass.locate("entity/seagull_ambient"),
            HBSoundEvents.SEAGULL_HURT to CommonClass.locate("entity/seagull_hurt"),
            HBSoundEvents.SEAGULL_DIE to CommonClass.locate("entity/seagull_die"),

            HBSoundEvents.OSTRICH_AMBIENT to CommonClass.locate("entity/ostrich_ambient"),
            HBSoundEvents.OSTRICH_HURT to CommonClass.locate("entity/ostrich_hurt"),
            
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