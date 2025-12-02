package silly.chemthunder.oculitis;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import silly.chemthunder.oculitis.event.OculosTooltipEvent;
import silly.chemthunder.oculitis.event.TextShakesEvent;

public class OculitisClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudLayerRegistrationCallback.EVENT.register(new TextShakesEvent());
        ItemTooltipCallback.EVENT.register(new OculosTooltipEvent());
    }
}
