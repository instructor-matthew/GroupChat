package com.matthew.fakeig.models;

import java.util.Date;

public class Greeting {

	  private String content;
	  private String sender;
	  private String image;
	  private Date timestamp;
	  private MessageStatus status;

	  public Greeting() {
	  }

	  public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Greeting(String content, String sender, String image, Date timestamp, MessageStatus status) {
		this.content = content;
		this.sender = sender;
		this.image = image;
		this.timestamp = timestamp;
		this.status = status;
	}


}
