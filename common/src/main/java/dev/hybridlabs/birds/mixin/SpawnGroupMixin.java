package dev.hybridlabs.birds.mixin;

import dev.hybridlabs.birds.utils.HybridBirdsSpawnGroup;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@SuppressWarnings("unused")
@Mixin(MobCategory.class)
public class SpawnGroupMixin {
    SpawnGroupMixin(String enumname, int ordinal, String name, int spawnCap, boolean peaceful, boolean rare,
                    int immediateDespawnRange) {
        throw new AssertionError();
    }

    // Vanilla Spawn Groups array
    @Shadow
    @Mutable
    @Final
    private static MobCategory[] $VALUES;

    @Unique
    private static MobCategory createHybridBirdsSpawnGroups(String enumname, int ordinal,
                                                              HybridBirdsSpawnGroup spawnGroup) {
        return ((MobCategory) (Object) new SpawnGroupMixin(spawnGroup.name, ordinal, spawnGroup.name,
                spawnGroup.spawnCap, spawnGroup.peaceful, spawnGroup.rare, spawnGroup.immediateDespawnRange));
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/MobCategory;" +
            "$VALUES:[Lnet/minecraft/world/entity/MobCategory;", shift = At.Shift.AFTER))
    private static void injectEnum(CallbackInfo ci) {
        int vanillaSpawnGroupsLength = $VALUES.length;
        for (MobCategory category : $VALUES) {
            HybridBirdsSpawnGroup.BY_NAME.put(category.name(), category);
        }
        HybridBirdsSpawnGroup[] haSpawnGroups = HybridBirdsSpawnGroup.values();
        $VALUES = Arrays.copyOf($VALUES, vanillaSpawnGroupsLength + haSpawnGroups.length);

        for (int i = 0; i < haSpawnGroups.length; i++) {
            int pos = vanillaSpawnGroupsLength + i;
            HybridBirdsSpawnGroup haSpawnGroup = haSpawnGroups[i];
            haSpawnGroup.spawnGroup = $VALUES[pos] = createHybridBirdsSpawnGroups(haSpawnGroup.name(), pos,
                    haSpawnGroup);

            HybridBirdsSpawnGroup.BY_NAME.put(haSpawnGroup.name(), haSpawnGroup.spawnGroup);
        }
    }
}
