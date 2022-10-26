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
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max=15)
	private String userName;
	@NotBlank
	@Size(max=30)
	private String fullName;
	@Email
	@NotBlank
	private String email;
	private String pic = "/images/default.jpg";
	@NotBlank
	private String password;
	@Transient
	private String confirmPassword;
	private OnlineStatus status;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="sender", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ChatMessage> sender; 
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name="conversations",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="chatroom_id")
			)
	private List<ChatRoom> chatrooms;
	
	public User() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<ChatMessage> getSender() {
		return sender;
	}

	public void setSender(List<ChatMessage> sender) {
		this.sender = sender;
	}

	public List<ChatRoom> getChatrooms() {
		return chatrooms;
	}

	public void setChatrooms(List<ChatRoom> chatrooms) {
		this.chatrooms = chatrooms;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic() {
		return pic;
	}

	public OnlineStatus getStatus() {
		return status;
	}

	public void setStatus(OnlineStatus status) {
		this.status = status;
	}
	
	
}
