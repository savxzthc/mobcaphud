package dev.savxz.mobcaphud.hud;

import dev.savxz.mobcaphud.MobCapHud;
import dev.savxz.mobcaphud.data.MobCapDataHolder;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.SpawnGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MobCapHudRenderer {
    private static final int GREEN = 0xFF55FF55;
    private static final int YELLOW = 0xFFFFFF55;
    private static final int RED = 0xFFFF5555;
    private static final int GRAY = 0xFFAAAAAA;
    private static final int BAR_WIDTH = 10;
    private static final int LABEL_WIDTH = 10;
    private static final SpawnGroupDisplay[] DISPLAYED_GROUPS = {
            new SpawnGroupDisplay(SpawnGroup.MONSTER, "Monster"),
            new SpawnGroupDisplay(SpawnGroup.CREATURE, "Creature"),
            new SpawnGroupDisplay(SpawnGroup.AMBIENT, "Ambient"),
            new SpawnGroupDisplay(SpawnGroup.AXOLOTLS, "Axolotl"),
            new SpawnGroupDisplay(SpawnGroup.UNDERGROUND_WATER_CREATURE, "Cave Fish"),
            new SpawnGroupDisplay(SpawnGroup.WATER_CREATURE, "Water Mob"),
            new SpawnGroupDisplay(SpawnGroup.WATER_AMBIENT, "Water Ambient")
    };

    private MobCapHudRenderer() {
    }

    public static void register() {
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> render(drawContext));
    }

    private static void render(DrawContext drawContext) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options.hudHidden || !MobCapHud.CONFIG.enabled || client.player == null) {
            return;
        }

        if (client.getServer() == null) {
            if (MobCapHud.CONFIG.showMultiplayerNotice) {
                renderRows(drawContext, List.of(new HudRow("MobCap HUD: Singleplayer only", GRAY)));
            }
            return;
        }

        Map<SpawnGroup, MobCapDataHolder.CategorySnapshot> snapshots = MobCapDataHolder.getLatest(client.world.getRegistryKey());
        List<HudRow> rows = new ArrayList<>();
        for (SpawnGroupDisplay display : DISPLAYED_GROUPS) {
            if (MobCapHud.CONFIG.isGroupVisible(display.group())) {
                rows.add(formatRow(display, snapshots.get(display.group())));
            }
        }

        if (!rows.isEmpty()) {
            renderRows(drawContext, rows);
        }
    }

    private static HudRow formatRow(SpawnGroupDisplay display, MobCapDataHolder.CategorySnapshot snapshot) {
        String label = String.format(Locale.ROOT, "%-" + LABEL_WIDTH + "s", display.name());
        if (snapshot == null) {
            return new HudRow(label + " " + emptyBar() + "  --/-- (--%)", GRAY);
        }

        double percentage = Math.min(100.0, snapshot.percentage());
        int filled = snapshot.cap() == 0 ? 0 : (int) Math.round(percentage / 100.0 * BAR_WIDTH);
        String bar = "█".repeat(filled) + "░".repeat(BAR_WIDTH - filled);
        String text = String.format(Locale.ROOT, "%s %s  %d/%d (%.0f%%)", label, bar, snapshot.current(), snapshot.cap(), percentage);
        return new HudRow(text, colorForPercentage(percentage));
    }

    private static void renderRows(DrawContext drawContext, List<HudRow> rows) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        int padding = 4;
        int lineGap = 2;
        int width = rows.stream()
                .map(HudRow::text)
                .mapToInt(textRenderer::getWidth)
                .max()
                .orElse(0);
        int lineHeight = textRenderer.fontHeight + lineGap;
        int height = rows.size() * lineHeight - lineGap;
        int backgroundColor = ((int) (MobCapHud.CONFIG.backgroundOpacity * 255.0f) << 24) | 0x101010;

        drawContext.getMatrices().pushMatrix();
        drawContext.getMatrices().translate(MobCapHud.CONFIG.hudX, MobCapHud.CONFIG.hudY);
        drawContext.getMatrices().scale(MobCapHud.CONFIG.scale, MobCapHud.CONFIG.scale);
        drawContext.fill(0, 0, width + padding * 2, height + padding * 2, backgroundColor);

        int y = padding;
        for (HudRow row : rows) {
            drawContext.drawText(textRenderer, row.text(), padding, y, row.color(), true);
            y += lineHeight;
        }

        drawContext.getMatrices().popMatrix();
    }

    private static int colorForPercentage(double percentage) {
        if (percentage >= 80.0) {
            return RED;
        }
        if (percentage >= 50.0) {
            return YELLOW;
        }
        return GREEN;
    }

    private static String emptyBar() {
        return "░".repeat(BAR_WIDTH);
    }

    private record SpawnGroupDisplay(SpawnGroup group, String name) {
    }

    private record HudRow(String text, int color) {
    }
}
