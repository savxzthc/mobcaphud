# MobCap HUD

MobCap HUD is a lightweight client-side Fabric mod for popular Minecraft Fabric versions. In singleplayer, it renders a compact overlay showing each spawn group's current population, calculated mob cap, and percentage fullness.

The HUD is useful for checking whether mob farms are close to cap and for diagnosing why spawning has slowed down.

## Features

- Real-time singleplayer mob-cap counts by spawn group
- Compact 10-step fullness bar with green, yellow, and red status colors
- Toggle key with on-screen confirmation
- Mod Menu configuration screen with tooltips
- Adjustable position, scale, and background opacity
- Optional multiplayer notice instead of stale or misleading data

## Supported Versions

This workspace builds separate jars for:

- Minecraft 1.20.1
- Minecraft 1.20.4
- Minecraft 1.20.6
- Minecraft 1.21.1
- Minecraft 1.21.4
- Minecraft 1.21.5
- Minecraft 1.21.8
- Minecraft 1.21.11

Use the jar that matches your Minecraft version.

## Requirements

- Fabric API matching your Minecraft version
- Java 17 for Minecraft 1.20.1 and 1.20.4
- Java 21 for Minecraft 1.20.6 and newer

Cloth Config is bundled in the mod jar. Mod Menu is optional and enables the in-game config screen.

## Usage

Install the matching built jar from `versions/<minecraft-version>/build/libs/` into your `mods` folder alongside Fabric API. Start Minecraft with the Fabric profile.

Press `H` in game to toggle the HUD on or off. A short on-screen message confirms the new state, and the setting is saved to `config/mobcaphud.json`.

The HUD only displays live mob-cap data in singleplayer because the integrated server is available in the same JVM. On multiplayer servers it can show a small `MobCap HUD: Singleplayer only` notice, which can be disabled in the config.

MobCap HUD does not upload telemetry, analytics, or gameplay data to any remote server.

## Configuration

With Mod Menu installed, open the MobCap HUD settings screen to change:

- HUD visibility
- HUD X/Y position
- Background opacity
- HUD scale
- Multiplayer notice visibility
- Toggle message visibility
- Visible spawn groups

You can also edit `config/mobcaphud.json` directly while Minecraft is closed.

## Troubleshooting

- If you see `MobCap HUD: Singleplayer only`, the mod is working but cannot read mob-cap data from the server you joined.
- If the HUD does not appear, press `H`, check that the Minecraft HUD is not hidden with F1, and confirm the mod is enabled in the config.
- If the wrong jar is installed, Minecraft may reject the mod at launch. Use the jar whose filename matches your Minecraft version.

## Building

Run:

```sh
./gradlew build
```

Each publishable jar is written under its version folder, for example:

```text
versions/1.21.1/build/libs/mobcaphud-1.21.1-1.0.1.jar
```

## Version Matrix

| Minecraft | Fabric Loader | Fabric API | Java |
|---|---:|---:|---:|
| 1.20.1 | 0.16.10+ | 0.92.9+1.20.1 | 17+ |
| 1.20.4 | 0.15.1+ | 0.97.3+1.20.4 | 17+ |
| 1.20.6 | 0.15.6+ | 0.100.8+1.20.6 | 21+ |
| 1.21.1 | 0.15.11+ | 0.116.12+1.21.1 | 21+ |
| 1.21.4 | 0.16.9+ | 0.119.4+1.21.4 | 21+ |
| 1.21.5 | 0.16.10+ | 0.128.2+1.21.5 | 21+ |
| 1.21.8 | 0.16.13+ | 0.136.1+1.21.8 | 21+ |
| 1.21.11 | 0.17.3+ | 0.141.4+1.21.11 | 21+ |

## License

MIT

Cloth Config is bundled unmodified under GNU LGPLv3. See `THIRD_PARTY_NOTICES.md`.
