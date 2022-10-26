package com.matthew.fakeig.models;


public class ChatNotification {
	private Long id;
	private String senderId;
	private String senderName;
	public ChatNotification() {
		
	}
	
	public ChatNotification(Long id, String senderId, String senderName) {
		this.id = id;
		this.senderId = senderId;
		this.senderName = senderName;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	
}
