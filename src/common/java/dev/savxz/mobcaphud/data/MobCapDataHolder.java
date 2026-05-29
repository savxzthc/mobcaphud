package dev.savxz.mobcaphud.data;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class MobCapDataHolder {
    public record CategorySnapshot(int current, int cap) {
        public double percentage() {
            return cap == 0 ? 0.0 : (double) current / cap * 100.0;
        }
    }

    private static volatile Map<RegistryKey<World>, Map<SpawnGroup, CategorySnapshot>> latestByWorld = Collections.emptyMap();

    private MobCapDataHolder() {
    }

    public static void update(RegistryKey<World> worldKey, Map<SpawnGroup, CategorySnapshot> snapshot) {
        EnumMap<SpawnGroup, CategorySnapshot> copy = new EnumMap<>(SpawnGroup.class);
        copy.putAll(snapshot);

        Map<RegistryKey<World>, Map<SpawnGroup, CategorySnapshot>> updated = new HashMap<>(latestByWorld);
        updated.put(worldKey, Collections.unmodifiableMap(copy));
        latestByWorld = Collections.unmodifiableMap(updated);
    }

    public static void clear() {
        latestByWorld = Collections.emptyMap();
    }

    public static Map<SpawnGroup, CategorySnapshot> getLatest(RegistryKey<World> worldKey) {
        return latestByWorld.getOrDefault(worldKey, Collections.emptyMap());
    }
}
