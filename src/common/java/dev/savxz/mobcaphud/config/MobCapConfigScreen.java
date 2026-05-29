package dev.savxz.mobcaphud.config;

import dev.savxz.mobcaphud.MobCapHud;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.Locale;

public class MobCapConfigScreen {
    private MobCapConfigScreen() {
    }

    public static Screen buildScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("config.mobcaphud.title"))
                .setSavingRunnable(MobCapHud.CONFIG::save);

        ConfigEntryBuilder entries = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("config.mobcaphud.category.general"));
        general.addEntry(entries.startBooleanToggle(Text.translatable("config.mobcaphud.enabled"), MobCapHud.CONFIG.enabled)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("config.mobcaphud.enabled.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.enabled = value)
                .build());
        general.addEntry(entries.startIntSlider(Text.translatable("config.mobcaphud.hudX"), MobCapHud.CONFIG.hudX, 0, 2000)
                .setDefaultValue(4)
                .setTooltip(Text.translatable("config.mobcaphud.hudX.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.hudX = value)
                .build());
        general.addEntry(entries.startIntSlider(Text.translatable("config.mobcaphud.hudY"), MobCapHud.CONFIG.hudY, 0, 2000)
                .setDefaultValue(4)
                .setTooltip(Text.translatable("config.mobcaphud.hudY.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.hudY = value)
                .build());
        general.addEntry(entries.startIntSlider(Text.translatable("config.mobcaphud.backgroundOpacity"), toSliderValue(MobCapHud.CONFIG.backgroundOpacity), 0, 100)
                .setDefaultValue(40)
                .setTextGetter(MobCapConfigScreen::percentText)
                .setTooltip(Text.translatable("config.mobcaphud.backgroundOpacity.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.backgroundOpacity = fromSliderValue(value))
                .build());
        general.addEntry(entries.startIntSlider(Text.translatable("config.mobcaphud.scale"), toSliderValue(MobCapHud.CONFIG.scale), 50, 300)
                .setDefaultValue(100)
                .setTextGetter(MobCapConfigScreen::scaleText)
                .setTooltip(Text.translatable("config.mobcaphud.scale.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.scale = fromSliderValue(value))
                .build());
        general.addEntry(entries.startBooleanToggle(Text.translatable("config.mobcaphud.showMultiplayerNotice"), MobCapHud.CONFIG.showMultiplayerNotice)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("config.mobcaphud.showMultiplayerNotice.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.showMultiplayerNotice = value)
                .build());
        general.addEntry(entries.startBooleanToggle(Text.translatable("config.mobcaphud.showToggleMessage"), MobCapHud.CONFIG.showToggleMessage)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("config.mobcaphud.showToggleMessage.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.showToggleMessage = value)
                .build());

        ConfigCategory categories = builder.getOrCreateCategory(Text.translatable("config.mobcaphud.category.spawns"));
        categories.addEntry(entries.startBooleanToggle(Text.translatable("config.mobcaphud.showMonster"), MobCapHud.CONFIG.showMonster)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("config.mobcaphud.spawnGroup.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.showMonster = value)
                .build());
        categories.addEntry(entries.startBooleanToggle(Text.translatable("config.mobcaphud.showCreature"), MobCapHud.CONFIG.showCreature)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("config.mobcaphud.spawnGroup.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.showCreature = value)
                .build());
        categories.addEntry(entries.startBooleanToggle(Text.translatable("config.mobcaphud.showAmbient"), MobCapHud.CONFIG.showAmbient)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("config.mobcaphud.spawnGroup.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.showAmbient = value)
                .build());
        categories.addEntry(entries.startBooleanToggle(Text.translatable("config.mobcaphud.showAxolotls"), MobCapHud.CONFIG.showAxolotls)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("config.mobcaphud.spawnGroup.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.showAxolotls = value)
                .build());
        categories.addEntry(entries.startBooleanToggle(Text.translatable("config.mobcaphud.showUndergroundWater"), MobCapHud.CONFIG.showUndergroundWater)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("config.mobcaphud.spawnGroup.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.showUndergroundWater = value)
                .build());
        categories.addEntry(entries.startBooleanToggle(Text.translatable("config.mobcaphud.showWater"), MobCapHud.CONFIG.showWater)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("config.mobcaphud.spawnGroup.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.showWater = value)
                .build());
        categories.addEntry(entries.startBooleanToggle(Text.translatable("config.mobcaphud.showWaterAmbient"), MobCapHud.CONFIG.showWaterAmbient)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("config.mobcaphud.spawnGroup.tooltip"))
                .setSaveConsumer(value -> MobCapHud.CONFIG.showWaterAmbient = value)
                .build());

        return builder.build();
    }

    private static int toSliderValue(float value) {
        return Math.round(value * 100.0f);
    }

    private static float fromSliderValue(int value) {
        return value / 100.0f;
    }

    private static Text percentText(int value) {
        return Text.literal(String.format(Locale.ROOT, "%d%%", value));
    }

    private static Text scaleText(int value) {
        return Text.literal(String.format(Locale.ROOT, "%.2fx", fromSliderValue(value)));
    }
}
