package me.morowo.MOrowoAddons.config;

import com.google.gson.*;
import me.morowo.MOrowoAddons.core.MOrowoAddons;
import me.morowo.MOrowoAddons.utils.Category;
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

    public static String addChild;

    public GeneralConfig(MOrowoAddons main, File settingsConfigFile) {
        this.main = main;
        this.settingsConfigFile = settingsConfigFile;
    }

    public static void init(File file) {
        GeneralConfig.config = new Configuration(file);
        GeneralConfig.syncConfig(true);

    }

    private static List<String> addMainSetting() {
        List<String> propOrder = new ArrayList();
        Property prop = getProperty(Category.MAIN, "AutoParty:", true);
        addChild = prop.getString();
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

    public static Property getProperty(Category category, String name, boolean defaultValue) {
        return config.get(category.getName(), name, defaultValue);
    }

    public static Property getProperty(Category category, String name, String defaultValue) {
        return config.get(category.getName(), name, defaultValue);
    }

    public static Configuration getConfig() {
        return config;
    }

    public static void setProperty(Category category, String name, boolean value) {
        config.get(category.getName(), name, true).set(value);
    }

    public static void setProperty(Category category, String name, String value) {
        config.get(category.getName(), name, true).set(value);
    }
}
