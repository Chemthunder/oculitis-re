package silly.chemthunder.oculitis.indexed;

import net.acoyt.acornlib.api.items.AcornItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import silly.chemthunder.oculitis.Oculitis;
import silly.chemthunder.oculitis.item.OculosFlosItem;

import java.util.function.Function;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

public interface OculitisItems {
    Item OCULOS_FLOS = create("oculos_flos", OculosFlosItem::new, new AcornItemSettings()
            .undroppable()
            .maxCount(1)
            .sword(ToolMaterial.NETHERITE, 4.5f, -2.8f)
    );

    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, Oculitis.id(name)), factory, settings);
    }

    static void index() {
        modifyItemNameColor(OCULOS_FLOS, 0x51336e);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(OCULOS_FLOS);
        });
    }
}
