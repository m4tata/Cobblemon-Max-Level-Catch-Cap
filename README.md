For Cobblemon Version 1.7+

🛑 Progression Cap: You cannot skip ahead in the game by getting lucky with a high-level catch. You must train your team to catch stronger Pokémon. If your highest level Pokémon is 15, you cannot catch a level 20 Pokémon.

↩️ Bounce Mechanic: If a target is too strong, the Poké Ball will physically bounce off the Pokémon.

💬 Feedback: An action-bar message appears telling you the max level you are currently allowed to catch (e.g., "This Pokémon is too strong! Max Catch Level: 15").

🛡️ Starter Protection: Even if you have no Pokémon, you can always catch Pokémon up to Level 5.

✨ Creative Bypass: Players in Creative Mode ignore this cap.

## Configuration

A simple properties file is created in the `config` folder when the mod first runs. It looks like this:

```
defaultLevelCap=5
extraLevelAllowance=0
```

- `defaultLevelCap` – level cap applied when you have no Pokémon (or all party Pokémon are below that level).
- `extraLevelAllowance` – number of levels above your highest party Pokémon that you are still permitted to catch.

Modify the values and restart the game to change the behaviour.
