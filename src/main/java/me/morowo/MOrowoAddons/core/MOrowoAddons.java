package me.morowo.MOrowoAddons.core;

import java.io.*;

import me.morowo.MOrowoAddons.command.MOrowoCommand;
import me.morowo.MOrowoAddons.command.WarpCommand;
import me.morowo.MOrowoAddons.command.joinBedwarsCommand;
import me.morowo.MOrowoAddons.config.GeneralConfig;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = MOrowoAddons.MODID, version = MOrowoAddons.VERSION, clientSideOnly = true)
public class MOrowoAddons
{
    public static final String MODID = "AutoThrower";
    public static final String VERSION = "1.0";
    private static MOrowoAddons instance;
    private static final Logger LOGGER = LogManager.getLogger();
    private GeneralConfig Config;

    public static MOrowoAddons getInstance() {
        return instance;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        GeneralConfig.init(new File(event.getSuggestedConfigurationFile(), "autokickthrower.cfg"));
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(event);

        ClientCommandHandler.instance.registerCommand(new MOrowoCommand());
        ClientCommandHandler.instance.registerCommand(new WarpCommand());
        ClientCommandHandler.instance.registerCommand(new joinBedwarsCommand());
    }

}
