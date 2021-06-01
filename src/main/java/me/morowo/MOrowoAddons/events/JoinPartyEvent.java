package me.morowo.MOrowoAddons.events;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoinPartyEvent {
    private static final Pattern PARTY_MESSAGE = Pattern.compile("[^a-z A-Z:0-9/'] has invited you to join their party!\n You have 60 seconds to accept.");
    private final Minecraft mc;

    public JoinPartyEvent(Minecraft mc) {
        this.mc = Minecraft.getMinecraft();
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        if (this.mc.thePlayer != null) {

        }
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent e) {
        String formattedMessage = e.message.getFormattedText();
        if (e.message == null) {
            Matcher partyMessage = JoinPartyEvent.PARTY_MESSAGE.matcher(formattedMessage);
            if (partyMessage.matches()) {
//                this.mc.thePlayer.sendChatMessage("/party accept ");
                for (IChatComponent message : e.message.getSiblings()) {
                    if (message.getChatStyle().getChatClickEvent() != null) {
                        message.getChatStyle().getChatClickEvent().getValue();
                    }
                }
            }
        }
    }
}

