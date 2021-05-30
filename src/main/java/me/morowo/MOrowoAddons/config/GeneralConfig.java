package me.morowo.MOrowoAddons.config;

import com.google.gson.*;
import me.morowo.MOrowoAddons.core.MOrowoAddons;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralConfig {

    public static final String MAIN_SETTINGS = "autokickthrower_main_settings";
    private static File file;
    private static Configuration config;
    private final MOrowoAddons main;
    private final File settingsConfigFile;
    private JsonObject settingsConfig = new JsonObject();

    public static String addPlayer;

    public GeneralConfig(MOrowoAddons main, File settingsConfigFile) {
        this.main = main;
        this.settingsConfigFile = settingsConfigFile;
    }

    public static void init(File file) {
        GeneralConfig.config = new Configuration(file);
        GeneralConfig.syncConfig(true);

    }

    private static List<String> addMainSetting()
    {
        Property prop;
        List<String> propOrder = new ArrayList<String>();

        prop = GeneralConfig.getProperty(GeneralConfig.MAIN_SETTINGS, "playerList:", "Poomy1234");
        GeneralConfig.addPlayer = prop.getString();
        propOrder.add(prop.getName());

        return propOrder;
    }

    public static void syncConfig(boolean load)
    {
        if (!GeneralConfig.config.isChild)
        {
            if (load)
            {
                GeneralConfig.config.load();
            }
        }

        GeneralConfig.config.setCategoryPropertyOrder(GeneralConfig.MAIN_SETTINGS, GeneralConfig.addMainSetting());

        if (GeneralConfig.config.hasChanged())
        {
            GeneralConfig.config.save();
        }
    }

    public static Property getProperty(String category, String name, boolean defaultValue)
    {
        return GeneralConfig.config.get(category, name, defaultValue);
    }

    public static Property getProperty(String category, String name, String defaultValue)
    {
        return GeneralConfig.config.get(category, name, defaultValue);
    }

    public static Configuration getConfig()
    {
        return GeneralConfig.config;
    }

}
