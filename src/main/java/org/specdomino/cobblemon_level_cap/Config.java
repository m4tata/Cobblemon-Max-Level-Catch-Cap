package org.specdomino.cobblemon_level_cap;

import net.fabricmc.loader.api.FabricLoader;
import java.io.*;
import java.nio.file.Path;
import java.util.Properties;

public class Config {
    // default values can be changed here
    public static int defaultLevelCap = 5;
    public static int extraLevelAllowance = 0;

    public static void load() {
        try {
            Path configDir = FabricLoader.getInstance().getConfigDir();
            Path configFile = configDir.resolve("cobblemon_level_cap.properties");
            Properties props = new Properties();
            // create folder if missing
            File dirFile = configDir.toFile();
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            if (configFile.toFile().exists()) {
                try (InputStream in = new FileInputStream(configFile.toFile())) {
                    props.load(in);
                }
            } else {
                // populate defaults and save
                props.setProperty("defaultLevelCap", String.valueOf(defaultLevelCap));
                props.setProperty("extraLevelAllowance", String.valueOf(extraLevelAllowance));
                try (OutputStream out = new FileOutputStream(configFile.toFile())) {
                    props.store(out, "Cobblemon Level Cap Configuration");
                }
            }

            defaultLevelCap = Integer.parseInt(props.getProperty("defaultLevelCap", String.valueOf(defaultLevelCap)));
            extraLevelAllowance = Integer.parseInt(props.getProperty("extraLevelAllowance", String.valueOf(extraLevelAllowance)));
        } catch (Exception e) {
            Cobblemon_level_cap.LOGGER.error("Failed to load config, using defaults", e);
        }
    }
}
