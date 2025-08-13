package dev.hybridlabs.birds.mixin;

import dev.hybridlabs.birds.entity.ai.FollowRoosterGoal;
import dev.hybridlabs.birds.entity.bird.ChickEntity;
import dev.hybridlabs.birds.entity.bird.RoosterEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.passive.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
        this.goalSelector.add(6, new LookAtEntityGoal(this, RoosterEntity.class, 8.0f));
        this.goalSelector.add(6, new LookAtEntityGoal(this, ChickEntity.class, 8.0f));
    }
}
