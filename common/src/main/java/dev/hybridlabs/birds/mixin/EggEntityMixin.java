package dev.hybridlabs.birds.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes;
import dev.hybridlabs.birds.item.CustomEggItem;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ThrownEgg.class)
public abstract class EggEntityMixin extends ThrowableItemProjectile {
    private EggEntityMixin(EntityType<? extends ThrowableItemProjectile> type, Level world) {
        super(type, world);
    }

    @WrapMethod(method = "onHitEntity")
    private void onHitEntity(EntityHitResult entityHitResult, Operation<Void> original) {
        Level world = this.level();
        if (!world.isClientSide) {
            if (world.random.nextInt(8) == 0) {
                int i = 1;
                if (world.random.nextInt(32) == 0) {
                    i = 4;
                }

                for (int j = 0; j < i; ++j) {
                    EntityType<?> childType = getTypeForChild();
                    Entity childEntity = childType.create(world);
                    if (childEntity != null) {
                        if (childEntity instanceof AgeableMob passiveChildEntity) {
                            passiveChildEntity.setAge(-24000);
                        }

                        childEntity.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                        world.addFreshEntity(childEntity);
                    }
                }
            }

            world.broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Unique
    private EntityType<?> getTypeForChild() {
        ItemStack stack = this.getItem();
        if (stack.getItem() instanceof CustomEggItem eggItem) {
            return eggItem.getType();
        }
        return HybridBirdsEntityTypes.INSTANCE.getCHICK();
    }
}
