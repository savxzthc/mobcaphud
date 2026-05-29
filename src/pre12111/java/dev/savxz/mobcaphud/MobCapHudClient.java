package dev.savxz.mobcaphud;

import dev.savxz.mobcaphud.data.MobCapDataHolder;
import dev.savxz.mobcaphud.hud.MobCapHudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class MobCapHudClient implements ClientModInitializer {
    private static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.mobcaphud.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "category.mobcaphud"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null || client.getServer() == null) {
                MobCapDataHolder.clear();
            }

            while (toggleKey.wasPressed()) {
                MobCapHud.CONFIG.enabled = !MobCapHud.CONFIG.enabled;
                MobCapHud.CONFIG.save();
                if (MobCapHud.CONFIG.showToggleMessage) {
                    client.inGameHud.setOverlayMessage(toggleMessage(), false);
                }
            }
        });

        MobCapHudRenderer.register();
    }

    private static Text toggleMessage() {
        return Text.translatable(MobCapHud.CONFIG.enabled ? "message.mobcaphud.enabled" : "message.mobcaphud.disabled");
    }
}
