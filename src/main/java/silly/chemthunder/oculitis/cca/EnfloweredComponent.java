package silly.chemthunder.oculitis.cca;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import silly.chemthunder.oculitis.Oculitis;

public class  EnfloweredComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<EnfloweredComponent> KEY = ComponentRegistry.getOrCreate(Oculitis.id("enflowered"), EnfloweredComponent.class);
    private final LivingEntity player;
    public int enfloweredTicks = 0;

    public EnfloweredComponent(LivingEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    @Override
    public void tick() {
        if (enfloweredTicks > 0) {
            enfloweredTicks--;
            player.setGlowing(true);
            if (enfloweredTicks == 0) {
                sync();
            }
        } else {
            player.setGlowing(false);
        }
    }


    // nbt
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {this.enfloweredTicks = nbtCompound.getInt("enfloweredTicks", 0);}
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {nbtCompound.putInt("enfloweredTicks", enfloweredTicks);}
}
