package me.morowo.MOrowoAddons.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class Utils {
    /*
    Taken from ConfusingAddons
    https://github.com/confusinguser/ConfusingAddons
    */
    public static void sendMessageToPlayer(String message) {
        sendMessageToPlayer(new ChatComponentText(message));
    }

    /*
    Taken from ConfusingAddons
    https://github.com/confusinguser/ConfusingAddons
    */
    public static void sendMessageToPlayer(IChatComponent message) {
        ClientChatReceivedEvent event = new ClientChatReceivedEvent((byte) 1, fixChatComponentColors(message));
        MinecraftForge.EVENT_BUS.post(event); // Let other mods pick up the new message
        if (!event.isCanceled()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(event.message);
        }
    }

    /*
    Taken from ConfusingAddons
    https://github.com/confusinguser/ConfusingAddons
    */
    public static void sendMessageToPlayer(String message, EnumChatFormatting color) {
        sendMessageToPlayer(new ChatComponentText(message).setChatStyle(new ChatStyle().setColor(color)));
    }

    /*
    Taken from ConfusingAddons
    https://github.com/confusinguser/ConfusingAddons
    */
    public static IChatComponent fixChatComponentColors(IChatComponent input) {
        List<IChatComponent> components = new ArrayList<>(input.getSiblings());
        components.add(0, input);
        IChatComponent output = new ChatComponentText("");
        for (IChatComponent componentParent : components) {
            EnumChatFormatting colorToUse = EnumChatFormatting.WHITE;
            if (!(componentParent instanceof ChatComponentText)) {
                output.appendSibling(componentParent);
                continue;
            }
            List<String> temp = new ArrayList<>(Arrays.asList((componentParent.getChatStyle().getFormattingCode() + componentParent.getUnformattedTextForChat()).split("\u00A7(?=[a-fA-F0-9lLkKmMnNoOrR])")));
            List<ChatComponentText> componentChildren = new ArrayList<>();
            if (!temp.isEmpty() && !temp.get(0).isEmpty()) componentChildren.add((ChatComponentText) new ChatComponentText(temp.get(0)).setChatStyle(componentParent.getChatStyle().createDeepCopy()));
            componentChildren.addAll(temp.subList(1, temp.size()).stream().map(component ->
                    (ChatComponentText) new ChatComponentText("\u00A7" + component).setChatStyle(componentParent.getChatStyle().createDeepCopy())).collect(Collectors.toList()));

            int prevStyleCodeIndex = 15;
            for (ChatComponentText componentChild : componentChildren) {
                boolean randomStyle = false;
                boolean boldStyle = false;
                boolean strikethroughStyle = false;
                boolean underlineStyle = false;
                boolean italicStyle = false;
                int styleCodeIndex;
                char[] charArray;
                if (componentChild.getFormattedText().endsWith("§r")) charArray = componentChild.getFormattedText().substring(0, componentChild.getFormattedText().length() - 2).toCharArray();
                else charArray = componentChild.getFormattedText().toCharArray();
                for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
                    char c = charArray[i];
                    if (c == 167 && i + 1 < componentChild.getFormattedText().length()) {
                        styleCodeIndex = "0123456789abcdefklmnor".indexOf(componentChild.getFormattedText().toLowerCase(Locale.ENGLISH).charAt(i + 1));

                        if (styleCodeIndex < 16) {
                            randomStyle = false;
                            boldStyle = false;
                            strikethroughStyle = false;
                            underlineStyle = false;
                            italicStyle = false;

                            if (styleCodeIndex < 0) {
                                styleCodeIndex = 15;
                            }

                        } else if (styleCodeIndex == 16) {
                            randomStyle = true;
                        } else if (styleCodeIndex == 17) {
                            boldStyle = true;
                        } else if (styleCodeIndex == 18) {
                            strikethroughStyle = true;
                        } else if (styleCodeIndex == 19) {
                            underlineStyle = true;
                        } else if (styleCodeIndex == 20) {
                            italicStyle = true;
                        } else {
                            randomStyle = false;
                            boldStyle = false;
                            strikethroughStyle = false;
                            underlineStyle = false;
                            italicStyle = false;
                            styleCodeIndex = 15;
                        }

                        for (EnumChatFormatting testColor : EnumChatFormatting.values()) {
                            if (testColor.getColorIndex() == styleCodeIndex) {
                                colorToUse = testColor;
                                break;
                            }
                        }
                    }
                }
                ChatStyle chatStyle = componentChild.getChatStyle()
                        .setColor(colorToUse)
                        .setBold(boldStyle)
                        .setItalic(italicStyle)
                        .setObfuscated(randomStyle)
                        .setStrikethrough(strikethroughStyle)
                        .setUnderlined(underlineStyle);
                IChatComponent lemp = output.appendSibling(new ChatComponentText(EnumChatFormatting.getTextWithoutFormattingCodes(componentChild.getUnformattedText())).setChatStyle(chatStyle));
            }
        }
        return output;
    }
}
