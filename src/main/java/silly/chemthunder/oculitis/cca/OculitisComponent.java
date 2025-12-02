package silly.chemthunder.oculitis.cca;

import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import silly.chemthunder.oculitis.Oculitis;

import java.util.Random;

public class OculitisComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<OculitisComponent> KEY = ComponentRegistry.getOrCreate(Oculitis.id("oculitis"), OculitisComponent.class);
    public boolean oculitisState = false;
    public int oculitisForm = 0;
    private final PlayerEntity player;

    public OculitisComponent(PlayerEntity player) {
        this.player = player;
    }
    public void sync() {
        KEY.sync(this.player);
    }

    @Override
    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.oculitisState = nbt.getBoolean("oculitisState", false);
        this.oculitisForm = nbt.getInt("oculitisForm", 0);
    }

    @Override
    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putBoolean("oculitisState", oculitisState);
        nbt.putInt("oculitisForm", oculitisForm);
    }

    @Override
    public void tick() {
        if (oculitisForm >= 3) {
            Random rand = new Random();
            int random = rand.nextInt(6);

            switch (random) {
                case 0:
                    player.sendMessage(Text.translatable("oculitis_anger_1").withColor(0x3a264f), true);
                    break;
                case 1:
                    player.sendMessage(Text.translatable("oculitis_anger_2").withColor(0x3a264f), true);
                    break;
                case 2:
                    player.sendMessage(Text.translatable("oculitis_anger_3").withColor(0x3a264f), true);
                    break;
                case 3:
                    player.sendMessage(Text.translatable("oculitis_anger_4").withColor(0x3a264f), true);
                    break;
                case 4:
                    player.sendMessage(Text.translatable("oculitis_anger_5").withColor(0x3a264f), true);
                    break;
                case 5:
                    player.sendMessage(Text.translatable("oculitis_anger_6").withColor(0x3a264f), true);
                    break;
            }
        }
    }
}
