package me.rhin.openciv.networking.chat;

import java.util.ArrayList;

import me.rhin.openciv.Civilization;
import me.rhin.openciv.asset.SoundEnum;
import me.rhin.openciv.listener.SendChatMessageListener;
import me.rhin.openciv.shared.packet.type.SendChatMessagePacket;

public class ChatHandler implements SendChatMessageListener {

	private ArrayList<String> messages;

	public ChatHandler() {

		this.messages = new ArrayList<>();
	}

	@Override
	public void onSentChatMessage(SendChatMessagePacket packet) {
		String messageText = packet.getPlayerName() + ": " + packet.getMessage();
		messages.add(messageText);

		Civilization.getInstance().getSoundHandler().playEffect(SoundEnum.CHAT_NOTIFICATION);
	}

	public ArrayList<String> getSentMessages() {
		return messages;
	}
}
