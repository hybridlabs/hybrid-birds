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
            
            HBSoundEvents.DUCK_AMBIENT to CommonClass.locate("entity.duck.ambient"),
            HBSoundEvents.DUCK_HURT to CommonClass.locate("entity.duck.hurt"),
            HBSoundEvents.DUCK_DIE to CommonClass.locate("entity.duck.die"),
            
            HBSoundEvents.GOOSE_AMBIENT to CommonClass.locate("entity.goose.ambient"),
            HBSoundEvents.GOOSE_HURT to CommonClass.locate("entity.goose.hurt"),
            HBSoundEvents.GOOSE_DIE to CommonClass.locate("entity.goose.die"),

            HBSoundEvents.SWAN_AMBIENT to CommonClass.locate("entity.swan.ambient"),
            HBSoundEvents.SWAN_HURT to CommonClass.locate("entity.swan.hurt"),
            HBSoundEvents.SWAN_DIE to CommonClass.locate("entity.swan.die"),

            HBSoundEvents.TURKEY_AMBIENT to CommonClass.locate("entity.turkey.ambient"),
            HBSoundEvents.TURKEY_HURT to CommonClass.locate("entity.turkey.hurt"),
            HBSoundEvents.TURKEY_DIE to CommonClass.locate("entity.turkey.die"),

            HBSoundEvents.PEACOCK_AMBIENT to CommonClass.locate("entity.peacock.ambient"),
            HBSoundEvents.PEACOCK_HURT to CommonClass.locate("entity.peacock.hurt"),
            HBSoundEvents.PEACOCK_DIE to CommonClass.locate("entity.peacock.die"),

            HBSoundEvents.CHICK_AMBIENT to CommonClass.locate("entity.chick.ambient"),
            HBSoundEvents.CHICK_HURT to CommonClass.locate("entity.chick.hurt"),
            HBSoundEvents.CHICK_DIE to CommonClass.locate("entity.chick.die"),

            HBSoundEvents.ROOSTER_CALL to CommonClass.locate("entity.rooster.call"),
            HBSoundEvents.ROOSTER_AMBIENT to CommonClass.locate("entity.rooster.ambient"),
            HBSoundEvents.ROOSTER_HURT to CommonClass.locate("entity.rooster.hurt"),
            HBSoundEvents.ROOSTER_DIE to CommonClass.locate("entity.rooster.die"),

            HBSoundEvents.SEAGULL_AMBIENT to CommonClass.locate("entity.seagull.ambient"),
            HBSoundEvents.SEAGULL_HURT to CommonClass.locate("entity.seagull.hurt"),
            HBSoundEvents.SEAGULL_DIE to CommonClass.locate("entity.seagull.die"),

            HBSoundEvents.OSTRICH_AMBIENT to CommonClass.locate("entity.ostrich.ambient"),
            HBSoundEvents.OSTRICH_HURT to CommonClass.locate("entity.ostrich.hurt"),
            
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