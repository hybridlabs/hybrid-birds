package dev.hybridlabs.birds.mixin;

import dev.hybridlabs.birds.entity.HybridBirdsSpawnGroup;
import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@SuppressWarnings("unused")
@Mixin(SpawnGroup.class)
public class SpawnGroupMixin {
    SpawnGroupMixin(String enumname, int ordinal, String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        throw new AssertionError();
    }

    // Vanilla Spawn Groups array
    @Shadow @Mutable @Final
    private static SpawnGroup[] field_6301;

    @Unique
    private static SpawnGroup createHybridBirdsSpawnGroups(String enumname, int ordinal, HybridBirdsSpawnGroup spawnGroup) {
        String namespacedName = spawnGroup.createNamespacedName();
        return ((SpawnGroup)(Object) new SpawnGroupMixin(namespacedName, ordinal, namespacedName, spawnGroup.spawnCap, spawnGroup.peaceful, spawnGroup.rare, spawnGroup.immediateDespawnRange));
    }

    @Inject(method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/SpawnGroup;field_6301:[Lnet/minecraft/entity/SpawnGroup;",
                    shift = At.Shift.AFTER
            )
    )
    private static void injectEnum(CallbackInfo ci) {
        int vanillaSpawnGroupsLength = field_6301.length;
        HybridBirdsSpawnGroup[] haSpawnGroups = HybridBirdsSpawnGroup.values();
        field_6301 = Arrays.copyOf(field_6301, vanillaSpawnGroupsLength + haSpawnGroups.length);

        for (int i = 0; i < haSpawnGroups.length; i++) {
            int pos = vanillaSpawnGroupsLength + i;
            HybridBirdsSpawnGroup haSpawnGroup = haSpawnGroups[i];
            haSpawnGroup.spawnGroup = field_6301[pos] = createHybridBirdsSpawnGroups(haSpawnGroup.name(), pos, haSpawnGroup);
        }
    }
}
