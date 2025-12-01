package silly.chemthunder.oculitis.cca;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class OculitisComponents implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, OculitisComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(OculitisComponent::new);
        registry.beginRegistration(LivingEntity.class, EnfloweredComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(EnfloweredComponent::new);
    }
}
