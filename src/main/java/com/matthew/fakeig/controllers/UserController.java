package com.matthew.fakeig.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.matthew.fakeig.models.OnlineStatus;
import com.matthew.fakeig.models.User;
import com.matthew.fakeig.services.UserService;
import com.matthew.fakeig.validators.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator validator;

	
	@GetMapping("/")
	public String login(@ModelAttribute("user") User user) {
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		User newUser = this.uService.registerUser(user);		
		session.setAttribute("user__id", newUser.getId());

		return "redirect:/chat";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("loginEmail") String email, @RequestParam("loginPassword") String password, RedirectAttributes redirectAttrs, HttpSession session) {
		if(!this.uService.authenticateUser(email, password)) {
			redirectAttrs.addFlashAttribute("loginError", "Invalid Credentials");
			return "redirect:/";
		}
		
		User user = this.uService.getByEmail(email);
		this.uService.updateStatus(user, OnlineStatus.ONLINE);
		session.setAttribute("user__id", user.getId());
		return "redirect:/chat";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		User user = this.uService.findOneUser((Long)session.getAttribute("user__id"));
		this.uService.updateStatus(user, OnlineStatus.OFFLINE);
		session.invalidate();
		return "redirect:/";
	}
}
