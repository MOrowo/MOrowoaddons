package me.morowo.MOrowoAddons.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;

import java.util.Collections;
import java.util.List;

public class WarpCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "warp";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("w");
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return null;
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
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return getListOfStringsMatchingLastWord(args,  "home", "dungeon_hub");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {

        if (args.length < 1) {
            throw new WrongUsageException("/warp <args>");
        }

        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "h":
                    args[0] = "home";
                    break;
                case "dung":
                    args[0] = "dungeon_hub";
                    break;
                case "f":
                    args[0] = "desert";
                    break;
                case "jerry":
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/savethejerrys");
                    break;
                case "mine":
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/warpforge");
                    break;
            }
        }
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/warp" + " " + args[0]);
    }
}
