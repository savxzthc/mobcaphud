package dev.savxz.mobcaphud;

import dev.savxz.mobcaphud.config.MobCapConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobCapHud implements ModInitializer {
    public static final String MOD_ID = "mobcaphud";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static MobCapConfig CONFIG = new MobCapConfig();

    @Override
    public void onInitialize() {
        CONFIG = MobCapConfig.load();
    }
}
