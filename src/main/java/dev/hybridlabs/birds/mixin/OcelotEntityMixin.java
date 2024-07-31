package dev.hybridlabs.birds.mixin;

import dev.hybridlabs.birds.entity.bird.RoosterEntity;
import dev.hybridlabs.birds.entity.bird.TurkeyEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OcelotEntity.class)
public abstract class OcelotEntityMixin extends AnimalEntity {
    protected OcelotEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("HEAD"))
    protected void registerGoals(CallbackInfo ci) {
        this.goalSelector.add(0, new FleeEntityGoal<>(this, TurkeyEntity.class, 8.0F, 1.0, 1.25));
        this.goalSelector.add(0, new FleeEntityGoal<>(this, RoosterEntity.class, 8.0F, 1.0, 1.25));
    }
}