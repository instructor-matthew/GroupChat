package com.matthew.fakeig.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="chatrooms")
public class ChatRoom {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// One To Many Chat Messages
	@OneToMany(mappedBy="chatroom", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<ChatMessage> messages;
	
	@ManyToMany
	@JoinTable(
			name="conversations",
			joinColumns = @JoinColumn(name="chatroom_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List<User> usersInChat;
	
	public ChatRoom() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<ChatMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<ChatMessage> messages) {
		this.messages = messages;
	}
	public List<User> getUsersInChat() {
		return usersInChat;
	}
	public void setUsersInChat(List<User> usersInChat) {
		this.usersInChat = usersInChat;
	}


	
	
}
