package com.matthew.fakeig.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matthew.fakeig.models.ChatMessage;
import com.matthew.fakeig.models.ChatRoom;
import com.matthew.fakeig.models.User;
import com.matthew.fakeig.repositories.ChatRepository;
import com.matthew.fakeig.repositories.ChatRoomRepository;

@Service
public class ChatService {
	@Autowired
	private ChatRoomRepository cRRepo;
	@Autowired
	private ChatRepository cRepo;
	
	public Boolean chatExists(User sender) {
		return this.cRRepo.existsByUsersInChatContaining(sender);
	}
	
	public List<ChatRoom> getChat(User sender) {
		return this.cRRepo.findByUsersInChatContaining(sender);
	}
	
	public ChatRoom createChat(ChatRoom chatRoom) {
		return this.cRRepo.save(chatRoom);
	}
	
	public ChatMessage createMessage(ChatMessage cMessage) {
		return this.cRepo.save(cMessage);
	}
	
	
	public ChatRoom getOneChat(Long id) {
		return this.cRRepo.findById(id).orElse(null);
	}
}
