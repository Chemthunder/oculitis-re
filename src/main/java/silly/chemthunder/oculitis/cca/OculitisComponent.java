package silly.chemthunder.oculitis.cca;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import silly.chemthunder.oculitis.Oculitis;

public class OculitisComponent implements AutoSyncedComponent {
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

}
