package dev.savxz.mobcaphud.mixin;

import dev.savxz.mobcaphud.data.MobCapDataHolder;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.SpawnHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;
import java.util.Map;

@Mixin(ServerChunkManager.class)
public class ServerChunkManagerMixin {
    private static final int MOB_CAP_CHUNK_AREA = 17 * 17;

    @Shadow
    @Nullable
    private SpawnHelper.Info spawnInfo;

    @Shadow
    @Final
    private ServerWorld world;

    @Inject(method = "tickChunks", at = @At("RETURN"))
    private void mobcaphud$onTickChunks(CallbackInfo ci) {
        if (spawnInfo == null) {
            return;
        }

        int spawningChunkCount = spawnInfo.getSpawningChunkCount();
        Map<SpawnGroup, MobCapDataHolder.CategorySnapshot> snapshot = new EnumMap<>(SpawnGroup.class);
        for (SpawnGroup spawnGroup : SpawnGroup.values()) {
            int current = spawnInfo.getGroupToCount().getInt(spawnGroup);
            int cap = spawnGroup.getCapacity() * spawningChunkCount / MOB_CAP_CHUNK_AREA;
            snapshot.put(spawnGroup, new MobCapDataHolder.CategorySnapshot(current, cap));
        }

        MobCapDataHolder.update(world.getRegistryKey(), snapshot);
    }
}
