package silly.chemthunder.oculitis.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import silly.chemthunder.oculitis.cca.EnfloweredComponent;
import silly.chemthunder.oculitis.cca.OculitisComponent;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyReturnValue(method = "getArmor", at = @At("RETURN"))
    private int ocu$lessArmor(int original) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof PlayerEntity player) {
            if (EnfloweredComponent.KEY.get(player).enfloweredTicks > 0) {
                return (int) (original * 0.75);
            }
        }
        return original;
    }

    @WrapMethod(method = "heal")
    private void sillierHealFFS(float amount, Operation<Void> original) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof PlayerEntity player) {
            if (OculitisComponent.KEY.get(player).oculitisForm >= 3) {
                if (getWorld().isDay()) {
                    original.call(amount * 3);
                }
            } else {
                original.call(amount);
            }
        }
    }

    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float multiplyDamage(float value) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof PlayerEntity player) {
            if (OculitisComponent.KEY.get(player).oculitisForm >= 3) {
                return value + 0.5f;
            }
        }
        return value;
    }
}
