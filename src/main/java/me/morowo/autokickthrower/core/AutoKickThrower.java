package me.morowo.autokickthrower.core;

import java.io.*;

import me.morowo.autokickthrower.command.ThrowCommand;
import me.morowo.autokickthrower.config.GeneralConfig;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = AutoKickThrower.MODID, version = AutoKickThrower.VERSION, clientSideOnly = true)
public class AutoKickThrower
{
    public static final String MODID = "AutoThrower";
    public static final String VERSION = "1.0";
    private static AutoKickThrower instance;
    private static final Logger LOGGER = LogManager.getLogger();
    private GeneralConfig Config;

    public static AutoKickThrower getInstance() {
        return instance;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        GeneralConfig.init(new File(event.getModConfigurationDirectory(), "autokickthrower.cfg"));

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(event);

        ClientCommandHandler.instance.registerCommand(new ThrowCommand());
    }

}
