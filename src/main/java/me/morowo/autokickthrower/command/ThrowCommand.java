package me.morowo.autokickthrower.command;

import me.morowo.autokickthrower.config.GeneralConfig;
import me.morowo.autokickthrower.util.JsonUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.EnumChatFormatting;

public class ThrowCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "thrower";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/thrower";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2) {
            throw new WrongUsageException("/thrower <add/remove> <IGN>");
        }

        if (args.length == 2) {
            if (Minecraft.getMinecraft().thePlayer != null)
            {
                args[0] = args[0].toUpperCase() + "ED";

                Minecraft.getMinecraft().thePlayer.addChatMessage(JsonUtils.create(EnumChatFormatting.RED + args[0] +" Player " + EnumChatFormatting.GRAY + "'" +EnumChatFormatting.GREEN + args[1] + EnumChatFormatting.GRAY + "'" + " to thrower list"));

            }
        }

    }
}
