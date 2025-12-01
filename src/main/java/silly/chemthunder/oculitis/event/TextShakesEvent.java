package silly.chemthunder.oculitis.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.fabricmc.fabric.api.client.rendering.v1.LayeredDrawerWrapper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.joml.Quaternionf;
import silly.chemthunder.oculitis.Oculitis;
import silly.chemthunder.oculitis.cca.OculitisComponent;

public class TextShakesEvent implements HudLayerRegistrationCallback {
    @Override
    public void register(LayeredDrawerWrapper layeredDrawerWrapper) {
        layeredDrawerWrapper.attachLayerAfter(IdentifiedLayer.HOTBAR_AND_BARS, Oculitis.id("oculitis_text"), (context, tickCounter) -> {

            // countdown
            PlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null) {
                if (OculitisComponent.KEY.get(player).oculitisForm >= 2) {
                    MutableText mutableText = Text.literal("").formatted(Formatting.ITALIC);

                    // wiggles
                    int i = MinecraftClient.getInstance().textRenderer.getWidth(mutableText);
                    int j = (context.getScaledWindowWidth() - i) / 2;
                    int k = context.getScaledWindowHeight() - 59;
                    if (!MinecraftClient.getInstance().interactionManager.hasStatusBars()) {
                        k += 14;
                    }

                    float shouldOffset = (float) (Math.sin(MinecraftClient.getInstance().world.getTime() / 4f) * 2f);

                    Quaternionf quaternionf = new Quaternionf();
                    quaternionf.rotateXYZ(0f, 0, (float) (Math.cos(MinecraftClient.getInstance().world.getTime() / 8f) / 8f));
                    context.getMatrices().multiply(quaternionf, j + i / 2f, k, 0);
                    context.getMatrices().translate(0, shouldOffset, -6);
                    context.getMatrices().translate(0, -shouldOffset, 6);
                    context.getMatrices().translate(0, -shouldOffset, 6);
                    quaternionf.rotationYXZ(0, 0f, -(float) (Math.cos(MinecraftClient.getInstance().world.getTime() / 8f) / 8f));
                    context.getMatrices().multiply(quaternionf, j + i / 2f, k, 0);
                }
            }
        });
    }
}
