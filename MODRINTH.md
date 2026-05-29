# Modrinth Upload Notes

## Project Title

MobCap HUD

## Short Summary

Singleplayer mob-cap overlay for Fabric.

## Project Description

MobCap HUD is a lightweight Fabric mod that displays a small in-game overlay showing each spawn group's current population, calculated cap, and percentage fullness.

Use it in singleplayer to check whether mob farms are close to the mob cap and to diagnose why spawning has slowed down. Press H to toggle the HUD and get an immediate on-screen confirmation, or use Mod Menu to change position, scale, background opacity, toggle message visibility, multiplayer notice visibility, and visible spawn groups.

Critical information: live mob-cap data is singleplayer only because the mod reads the integrated server running inside the client. On multiplayer servers, it does not read server mob-cap data and only shows an optional "MobCap HUD: Singleplayer only" notice. The mod does not upload telemetry or other data to remote servers.

## Plain-Text Description

MobCap HUD is a lightweight Fabric mod that displays a compact in-game overlay for singleplayer mob-cap information. It shows each spawn group's current count, calculated cap, and percentage fullness so you can tell whether farms are close to cap or why spawning has slowed down. Press H to toggle the HUD and use Mod Menu to adjust position, scale, background opacity, toggle messages, multiplayer notice visibility, and visible spawn groups. Live mob-cap data is singleplayer only because the mod reads the integrated server running inside the client. On multiplayer servers, it does not read server mob-cap data and only shows an optional singleplayer-only notice. The mod does not upload telemetry or gameplay data to remote servers.

## Sides

- Client: required
- Server: unsupported

## Version Dependencies

Upload each jar as a separate Modrinth version for its matching Minecraft version.

| Minecraft | Required dependencies | Bundled | Optional |
|---|---|---|---|
| 1.20.1 | Fabric Loader 0.16.10+, Fabric API 0.92.9+1.20.1 | Cloth Config 11.1.136 | Mod Menu 7.2.2 |
| 1.20.4 | Fabric Loader 0.15.1+, Fabric API 0.97.3+1.20.4 | Cloth Config 13.0.138 | Mod Menu 9.2.0 |
| 1.20.6 | Fabric Loader 0.15.6+, Fabric API 0.100.8+1.20.6 | Cloth Config 14.0.139 | Mod Menu 10.0.0 |
| 1.21.1 | Fabric Loader 0.15.11+, Fabric API 0.116.12+1.21.1 | Cloth Config 15.0.140 | Mod Menu 11.0.4 |
| 1.21.4 | Fabric Loader 0.16.9+, Fabric API 0.119.4+1.21.4 | Cloth Config 17.0.144 | Mod Menu 13.0.4 |
| 1.21.5 | Fabric Loader 0.16.10+, Fabric API 0.128.2+1.21.5 | Cloth Config 18.0.145 | Mod Menu 14.0.2 |
| 1.21.8 | Fabric Loader 0.16.13+, Fabric API 0.136.1+1.21.8 | Cloth Config 19.0.147 | Mod Menu 15.0.2 |
| 1.21.11 | Fabric Loader 0.17.3+, Fabric API 0.141.4+1.21.11 | Cloth Config 21.11.153 | Mod Menu 17.0.0 |

## Compliance Notes

- No remote telemetry, analytics, upload, or download behavior is implemented.
- No x-ray, aim assist, movement modification, PvP automation, item duplication, or hacked-client behavior is implemented.
- No claims of Modrinth endorsement are made.
- Cloth Config is bundled as an unmodified LGPLv3 library; see `THIRD_PARTY_NOTICES.md`.
- Use only the normal jar for each version upload. Source jars should only be uploaded as additional files when intentionally providing source.
