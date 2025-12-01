package silly.chemthunder.oculitis.indexed;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import silly.chemthunder.oculitis.Oculitis;

public interface OculitisDamageSources {
    RegistryKey<DamageType> ENFLOWERED = of("enflowered");

    static DamageSource enflowered(Entity entity) {
        return entity.getDamageSources().create(ENFLOWERED); }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Oculitis.id(name));
    }
}
