package me.morowo.MOrowoAddons.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.morowo.MOrowoAddons.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class MOrowoCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "morowo";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/morowo";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] args) throws CommandException {
        Utils.sendMessageToPlayer(ChatFormatting.GREEN + "MOrowoAddons Command");
        Utils.sendMessageToPlayer("/bw <1/2/3/4 to join bedwars");
        Utils.sendMessageToPlayer("/warp <dung/h/f/jerry> ya know it");
    }
}
