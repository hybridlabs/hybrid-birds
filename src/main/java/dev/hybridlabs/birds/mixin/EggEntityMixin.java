package dev.hybridlabs.birds.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes;
import dev.hybridlabs.birds.item.CustomEggItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EggEntity.class)
public abstract class EggEntityMixin extends ThrownItemEntity {
    private EggEntityMixin(EntityType<? extends ThrownItemEntity> type, World world) {
        super(type, world);
    }

    @WrapMethod(method = "onCollision")
    private void onCollision(HitResult result, Operation<Void> original) {
        World world = this.getWorld();
        if (!world.isClient) {
            if (world.random.nextInt(8) == 0) {
                int i = 1;
                if (world.random.nextInt(32) == 0) {
                    i = 4;
                }

                for (int j = 0; j < i; ++j) {
                    EntityType<?> childType = getTypeForChild();
                    Entity childEntity = childType.create(world);
                    if (childEntity != null) {
                        if (childEntity instanceof PassiveEntity passiveChildEntity) {
                            passiveChildEntity.setBreedingAge(-24000);
                        }

                        childEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                        world.spawnEntity(childEntity);
                    }
                }
            }

            world.sendEntityStatus(this, (byte) 3);
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
