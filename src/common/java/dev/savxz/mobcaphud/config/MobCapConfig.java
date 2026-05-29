package dev.savxz.mobcaphud.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.savxz.mobcaphud.MobCapHud;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.SpawnGroup;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class MobCapConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("mobcaphud.json");

    public boolean enabled = true;
    public int hudX = 4;
    public int hudY = 4;
    public boolean showMonster = true;
    public boolean showCreature = true;
    public boolean showAmbient = true;
    public boolean showAxolotls = true;
    public boolean showUndergroundWater = true;
    public boolean showWater = true;
    public boolean showWaterAmbient = true;
    public boolean showMultiplayerNotice = true;
    public boolean showToggleMessage = true;
    public float backgroundOpacity = 0.4f;
    public float scale = 1.0f;

    public static MobCapConfig load() {
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH, StandardCharsets.UTF_8)) {
                MobCapConfig config = GSON.fromJson(reader, MobCapConfig.class);
                if (config != null) {
                    config.clampValues();
                    config.save();
                    return config;
                }
            } catch (IOException | RuntimeException exception) {
                MobCapHud.LOGGER.warn("Failed to load MobCap HUD config, recreating defaults", exception);
            }
        }

        MobCapConfig config = new MobCapConfig();
        config.save();
        return config;
    }

    public void save() {
        clampValues();
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH, StandardCharsets.UTF_8)) {
                GSON.toJson(this, writer);
            }
        } catch (IOException exception) {
            MobCapHud.LOGGER.warn("Failed to save MobCap HUD config", exception);
        }
    }

    public boolean isGroupVisible(SpawnGroup group) {
        return switch (group) {
            case MONSTER -> showMonster;
            case CREATURE -> showCreature;
            case AMBIENT -> showAmbient;
            case AXOLOTLS -> showAxolotls;
            case UNDERGROUND_WATER_CREATURE -> showUndergroundWater;
            case WATER_CREATURE -> showWater;
            case WATER_AMBIENT -> showWaterAmbient;
            default -> false;
        };
    }

    private void clampValues() {
        hudX = clamp(hudX, 0, 2000);
        hudY = clamp(hudY, 0, 2000);
        backgroundOpacity = clamp(backgroundOpacity, 0.0f, 1.0f);
        scale = clamp(scale, 0.5f, 3.0f);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }
}
