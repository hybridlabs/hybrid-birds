package dev.hybridlabs.birds.mixin;

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes;
import dev.hybridlabs.birds.entity.ai.FollowRoosterGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.passive.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChickenEntity.class)
public abstract class ChickenEntityMixin extends AnimalEntity {
    protected ChickenEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("HEAD"))
    protected void registerGoals(CallbackInfo ci) {
        this.goalSelector.add(0, new FollowRoosterGoal((ChickenEntity)(Object)this, 1.0, 4.0F, 8.0F));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, OcelotEntity.class, 8.0F, 1.0, 1.0));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, FoxEntity.class, 8.0F, 1.0, 1.0));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, CatEntity.class, 8.0F, 1.0, 1.0));
    }

    @Inject(method = "createChild*", at = @At("HEAD"), cancellable = true)
    private void injectCreateChild(ServerWorld serverWorld, PassiveEntity passiveEntity, CallbackInfoReturnable<PassiveEntity> cir) {
        cir.setReturnValue(HybridBirdsEntityTypes.getChickEntityType().create(serverWorld));
    }
}