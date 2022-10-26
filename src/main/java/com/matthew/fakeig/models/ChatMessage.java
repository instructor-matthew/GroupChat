package com.matthew.fakeig.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="chatmessages")
public class ChatMessage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// Which Chatroom the message belongs to
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="chatroom_id")
	private ChatRoom chatroom;
	
	//One to One Who Sent
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sender_id")
	private User sender;

	private String content;
	private Date timestamp;
	private MessageStatus status;
	
	@PrePersist
	protected void onCreate() {
		this.timestamp = new Date();
	}
	
	public ChatMessage(ChatRoom chatroom, User sender, String content, MessageStatus status) {
		this.chatroom = chatroom;
		this.sender = sender;
		this.content = content;
		this.status = status;
	}

	public ChatMessage() {
	
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public ChatRoom getChatroom() {
		return chatroom;
	}
	public void setChatroom(ChatRoom chatroom) {
		this.chatroom = chatroom;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public MessageStatus getStatus() {
		return status;
	}
	public void setStatus(MessageStatus status) {
		this.status = status;
	}
	
	
}
