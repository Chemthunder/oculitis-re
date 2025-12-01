package silly.chemthunder.oculitis;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import silly.chemthunder.oculitis.event.TextShakesEvent;

public class OculitisClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudLayerRegistrationCallback.EVENT.register(new TextShakesEvent());
    }
}
