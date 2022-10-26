package com.matthew.fakeig.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.matthew.fakeig.models.ChatRoom;
import com.matthew.fakeig.models.User;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long>{
	List<ChatRoom> findByUsersInChatContaining(User sender);
	Boolean existsByUsersInChatContaining(User sender);


}
