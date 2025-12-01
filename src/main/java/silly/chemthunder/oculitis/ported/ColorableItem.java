package silly.chemthunder.oculitis.ported;

import net.minecraft.item.ItemStack;

public interface ColorableItem {
    int startColor(ItemStack stack);
    int endColor(ItemStack stack);
    int backgroundColor(ItemStack stack);
}
