package com.matthew.fakeig.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.matthew.fakeig.models.ChatMessage;
import com.matthew.fakeig.models.MessageStatus;

@Repository
public interface ChatRepository extends CrudRepository<ChatMessage, Long>{
//	Long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);
//	List<ChatMessage> findByChatId(String chatId);
//	Boolean findBySenderIdAndRecipientId(String senderId, String recipientId);
}
