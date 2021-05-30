package me.morowo.MOrowoAddons.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class joinBedwarsCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "bw";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/bw <args>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] args) throws CommandException {
        if (args.length < 1) {
            throw new WrongUsageException("/bw <args>");
        }

        switch (args[0]) {
            case "1":
                args[0] = "bedwars_eight_one";
                break;
            case "2":
                args[0] = "bedwars_eight_two";
                break;
            case "3":
                args[0] = "bedwars_four_three";
                break;
            case "4":
                args[0] = "bedwars_four_four";
                break;
        }

        Minecraft.getMinecraft().thePlayer.sendChatMessage("/play" + " " + args[0]);
    }
}
