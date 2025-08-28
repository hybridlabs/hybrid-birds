package dev.hybridlabs.birds.mixin;

import dev.hybridlabs.birds.entity.bird.PeacockEntity;
import dev.hybridlabs.birds.entity.bird.RoosterEntity;
import dev.hybridlabs.birds.entity.bird.TurkeyEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Ocelot.class)
public abstract class OcelotEntityMixin extends Animal {
    protected OcelotEntityMixin(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "registerGoals", at = @At("HEAD"))
    protected void registerGoals(CallbackInfo ci) {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PeacockEntity.class, false));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, TurkeyEntity.class, 8.0F, 1.0, 1.25));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, RoosterEntity.class, 8.0F, 1.0, 1.25));
    }
}