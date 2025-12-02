package silly.chemthunder.oculitis.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import silly.chemthunder.oculitis.cca.OculitisComponent;
import silly.chemthunder.oculitis.item.OculosFlosItem;

import java.util.List;

public class OculosTooltipEvent implements ItemTooltipCallback {
    public void getTooltip(ItemStack stack, Item.TooltipContext tooltipContext, TooltipType tooltipType, List<Text> list) {
        if (stack.getItem() instanceof OculosFlosItem) {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            list.add(Text.translatable("lore.oculos_1").styled(style -> style.withColor(0xa37dca)));
            list.add(Text.translatable("lore.oculos_2").styled(style -> style.withColor(0xa37dca)));

            if (player != null) {
                if (OculitisComponent.KEY.get(player).oculitisForm >= 2) {
                    list.add(Text.translatable("lore.oculos_unlocked.1").styled(style -> style.withColor(0x3a264f).withFormatting(Formatting.ITALIC)));
                    list.add(Text.translatable("lore.oculos_unlocked.2").styled(style -> style.withColor(0x3a264f).withFormatting(Formatting.ITALIC)));
                }
                if (OculitisComponent.KEY.get(player).oculitisForm >= 3) {
                    list.add(Text.translatable("lore.oculos_unlocked.2_1").styled(style -> style.withColor(0x3a264f).withFormatting(Formatting.ITALIC)));
                    list.add(Text.translatable("lore.oculos_unlocked.2_2").styled(style -> style.withColor(0x3a264f).withFormatting(Formatting.ITALIC)));
                    list.add(Text.translatable("lore.oculos_unlocked.2_3").styled(style -> style.withColor(0x3a264f).withFormatting(Formatting.ITALIC)));
                }
            }
        }
    }
}