package me.morowo.MOrowoAddons.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.morowo.MOrowoAddons.config.GeneralConfig;
import me.morowo.MOrowoAddons.utils.Category;
import me.morowo.MOrowoAddons.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.IChatComponent;

import java.util.Collections;
import java.util.List;

public class PartyAcceptCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "paccept";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/paccept <on/off>";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("pa");
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] args) throws CommandException {
        if (args.length == 0) {
            boolean currState = GeneralConfig.getProperty(Category.MAIN, "AutoParty", true).getBoolean();
            GeneralConfig.setProperty(Category.MAIN, "AutoParty:", !currState);
            Utils.sendMessageToPlayer("AutoParty has been turned " + (currState ? "off" : "on") + "!");
        }
        GeneralConfig.getConfig().save();
    }
}
