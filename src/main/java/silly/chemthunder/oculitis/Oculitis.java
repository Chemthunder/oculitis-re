package silly.chemthunder.oculitis;

import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import silly.chemthunder.oculitis.indexed.OculitisItems;

public class Oculitis implements ModInitializer {
	public static final String MOD_ID = "oculitis";

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        OculitisItems.index();

		if (FabricLoader.getInstance().isDevelopmentEnvironment()) LOGGER.info("Debug features active");


        ALib.registerModMenu(MOD_ID, 0x51336e);
	}
}