package dev.hybridlabs.birds.mixin;

import dev.hybridlabs.birds.entity.ai.goal.FollowRoosterGoal;
import dev.hybridlabs.birds.entity.bird.ChickEntity;
import dev.hybridlabs.birds.entity.bird.RoosterEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chicken.class)
public abstract class ChickenEntityMixin extends Animal {
    private ChickenEntityMixin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void registerGoals(CallbackInfo ci) {
        this.goalSelector.addGoal(0, new FollowRoosterGoal((Chicken)(Object)this, 1.0, 4.0F, 8.0F));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Ocelot.class, 8.0F, 1.0, 1.0));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Fox.class, 8.0F, 1.0, 1.0));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Cat.class, 8.0F, 1.0, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, RoosterEntity.class, 8.0f));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, ChickEntity.class, 8.0f));
    }
}
