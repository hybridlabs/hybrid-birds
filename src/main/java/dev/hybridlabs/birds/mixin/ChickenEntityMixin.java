package dev.hybridlabs.birds.mixin;

import dev.hybridlabs.birds.entity.HybridBirdsEntityTypes;
import dev.hybridlabs.birds.entity.ai.FollowRoosterGoal;
import dev.hybridlabs.birds.entity.bird.RoosterEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChickenEntity.class)
public abstract class ChickenEntityMixin extends AnimalEntity {
    private ChickenEntityMixin(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "initGoals", at = @At("HEAD"))
    protected void registerGoals(CallbackInfo ci) {
        this.goalSelector.add(0, new FollowRoosterGoal((ChickenEntity)(Object)this, 1.0, 4.0F, 8.0F));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, OcelotEntity.class, 8.0F, 1.0, 1.0));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, FoxEntity.class, 8.0F, 1.0, 1.0));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, CatEntity.class, 8.0F, 1.0, 1.0));
    }

    @Inject(method = "createChild(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/PassiveEntity;)Lnet/minecraft/entity/passive/ChickenEntity;", at = @At("HEAD"), cancellable = true)
    private void injectCreateChild(ServerWorld world, PassiveEntity parentEntity, CallbackInfoReturnable<PassiveEntity> cir) {
        cir.setReturnValue(HybridBirdsEntityTypes.INSTANCE.getCHICK().create(world));
    }
}