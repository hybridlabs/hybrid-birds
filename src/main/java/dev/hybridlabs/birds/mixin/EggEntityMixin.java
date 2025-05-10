package dev.hybridlabs.birds.mixin;

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes;
import dev.hybridlabs.birds.entity.bird.ChickEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EggEntity.class)
public abstract class EggEntityMixin {

    @Inject(method = "onCollision", at = @At("HEAD"), cancellable = true)
    private void onCollision(HitResult hitResult, CallbackInfo ci) {
        EggEntity eggEntity = (EggEntity) (Object) this;
        World world = eggEntity.getWorld();
        if (!world.isClient) {
            if (world.random.nextInt(8) == 0) {
                int i = 1;
                if (world.random.nextInt(32) == 0) {
                    i = 4;
                }

                for (int j = 0; j < i; ++j) {
                    ChickEntity chickEntity = HybridBirdsEntityTypes.INSTANCE.getCHICK().create(world);
                    if (chickEntity != null) {
                        chickEntity.setBreedingAge(-24000);
                        chickEntity.refreshPositionAndAngles(eggEntity.getX(), eggEntity.getY(), eggEntity.getZ(), eggEntity.getYaw(), 0.0F);
                        world.spawnEntity(chickEntity);
                    }
                }
            }

            world.sendEntityStatus(eggEntity, (byte) 3);
            eggEntity.discard();
        }

        ci.cancel();
    }
}
