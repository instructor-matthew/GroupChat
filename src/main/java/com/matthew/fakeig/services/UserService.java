package com.matthew.fakeig.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matthew.fakeig.models.OnlineStatus;
import com.matthew.fakeig.models.User;
import com.matthew.fakeig.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	
	public User findOneUser(Long id) {
		User user = this.uRepo.findById(id).orElse(null);
		return user;
	}
	
	public List<User> allUsers(){
		return this.uRepo.findAll();
	}
	
	public List<User> search(String name){
		return this.uRepo.findByFullNameLikeIgnoreCase(name);
	}
	
	public void updateStatus(User user, OnlineStatus status) {
		user.setStatus(status);
		this.uRepo.save(user);
	}
	
	// Register A User and Hash the Password
	public User registerUser(User user) {
		// Generate The Hash
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		// Set the hashed password on the users password field
		user.setPassword(hash);
		// Save that new user password to the database
		return this.uRepo.save(user);
	}
	
	// Login / Authenticate
	public boolean authenticateUser(String email, String password) {
		// Make sure the user logging in is who they say they are
		// Step 1: try and query for the user by email
		User user = this.uRepo.findByEmail(email);
		
		if(user == null) {
			return false;
		}
		
		// Step 2: check provided email against email in the database for user
		return BCrypt.checkpw(password, user.getPassword());
	}
	
	public User getByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
	

}
