package silly.chemthunder.oculitis.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import silly.chemthunder.oculitis.cca.EnfloweredComponent;
import silly.chemthunder.oculitis.cca.OculitisComponent;
import silly.chemthunder.oculitis.indexed.OculitisItems;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addCritParticles(Lnet/minecraft/entity/Entity;)V"))
    private void customCritEffect(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getStackInHand(Hand.MAIN_HAND).isOf(OculitisItems.OCULOS_FLOS)) {
            if (OculitisComponent.KEY.get(player).oculitisForm >= 2) { // if player's oculitis level is 2 or 3
                EnfloweredComponent enfloweredComponentVictim = EnfloweredComponent.KEY.get(target);

                enfloweredComponentVictim.enfloweredTicks = 200;
                enfloweredComponentVictim.sync();
            }
        }
    }

    @ModifyReturnValue(method = "getHurtSound", at = @At("RETURN"))
    private SoundEvent newHitSound(SoundEvent original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (OculitisComponent.KEY.get(player).oculitisForm >= 1) {
            return SoundEvents.BLOCK_AZALEA_BREAK;
        }
        return original;
    }

    @ModifyReturnValue(method = "getSplashSound", at = @At("RETURN"))
    private SoundEvent newSplashSound(SoundEvent original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (OculitisComponent.KEY.get(player).oculitisForm >= 1) {
            return SoundEvents.BLOCK_CHERRY_LEAVES_PLACE;
        }
        return original;
    }

    @ModifyReturnValue(method = "getDeathSound", at = @At("RETURN"))
    private SoundEvent newDeathSound(SoundEvent original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (OculitisComponent.KEY.get(player).oculitisForm >= 1) {
            return SoundEvents.ENTITY_WITHER_HURT;
        }
        return original;
    }

    @ModifyReturnValue(method = "getMovementSpeed", at = @At("RETURN"))
    private float oculitisIncreaseSpeed(float original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (OculitisComponent.KEY.get(player).oculitisForm >= 3) {
            return original + 0.07f;
        }
        return original;
    }
}
