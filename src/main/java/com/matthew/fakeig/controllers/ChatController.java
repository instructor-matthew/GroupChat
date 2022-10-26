package com.matthew.fakeig.controllers;


import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.matthew.fakeig.models.ChatMessage;
import com.matthew.fakeig.models.ChatRoom;
import com.matthew.fakeig.models.Greeting;
import com.matthew.fakeig.models.HelloMessage;
import com.matthew.fakeig.models.MessageStatus;
import com.matthew.fakeig.models.User;
import com.matthew.fakeig.services.ChatService;
import com.matthew.fakeig.services.UserService;



@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private UserService uService;
	@Autowired
	private ChatService cService;

	
	@GetMapping("")
	public String dashboard(Model viewModel, HttpSession session) {
		User loggedIn = this.uService.findOneUser((Long)session.getAttribute("user__id"));
		System.out.println(this.cService.getChat(loggedIn));
		viewModel.addAttribute("loggedUser", this.uService.findOneUser((Long)session.getAttribute("user__id")));
		viewModel.addAttribute("allChats", this.cService.getChat(loggedIn));
		return "dashboard.jsp";
	}
	
	
	
	@GetMapping("/{chatId}")
	public String chat(@ModelAttribute("chatmessage") ChatMessage chatmessage, Model viewModel, HttpSession session, @PathVariable("chatId") Long chatId) {
		viewModel.addAttribute("chatRoom", this.cService.getOneChat(chatId));
		viewModel.addAttribute("loggedInUser", this.uService.findOneUser((Long)session.getAttribute("user__id")));		
		return "chat.jsp";		
	}
	
	@PostMapping("/{chatId}")
	public String sendMsg(@Valid @ModelAttribute("chatmessage") ChatMessage chatmessage, BindingResult result, @PathVariable("chatId") Long chatId, HttpSession session) {
		User sender = this.uService.findOneUser((Long)session.getAttribute("user__id"));
		chatmessage.setSender(sender);
		chatmessage.setChatroom(this.cService.getOneChat(chatId));
		chatmessage.setStatus(MessageStatus.DELIVERED);
		this.cService.createMessage(chatmessage);
		return "redirect:/chat/{chatId}";
	}
	
	@GetMapping("/new")
	public String newChat(HttpSession session, Model viewModel) {
		viewModel.addAttribute("allUsers", this.uService.allUsers());
		viewModel.addAttribute("logged", this.uService.findOneUser((Long)session.getAttribute("user__id")));
		return "new.jsp";
	}
	
	@PostMapping("/new")
	public String createChat(@RequestParam(name="chat1", required=true) Long id1, @RequestParam(name="chat2", required=false) Long id2, @RequestParam(name="chat3", required=false) Long id3, HttpSession session) {
		ChatRoom chat = new ChatRoom();
		ArrayList<User> users = new ArrayList<User>();
		users.add(this.uService.findOneUser(id1));
		if(id2 != null) {
			users.add(this.uService.findOneUser(id2));
		}
		if(id3 != null) {
			users.add(this.uService.findOneUser(id3));
		}
		users.add(this.uService.findOneUser((Long)session.getAttribute("user__id")));
		chat.setUsersInChat(users);
		ChatRoom added = this.cService.createChat(chat);
		return "redirect:/chat/" + added.getId();
	}
	
	@PostMapping("/new2")
	public String createTest(@RequestParam("test") String nums, HttpSession session) {
		System.out.println(nums);
		return "sss";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public List<User> search(@RequestParam(value="value", required = false, defaultValue="") String val) {
		List<User> results = this.uService.search(val);
		return results;
	}
	
	@MessageMapping("/hello/{id}")
	@SendTo("/topic/greetings/{id}")
	public Greeting saySomething(@Payload HelloMessage message, @DestinationVariable String id, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		String sessionId = headerAccessor.getSessionAttributes().get("user__id").toString();
		User user = this.uService.findOneUser(Long.parseLong(sessionId));
		ChatRoom cRoom = this.cService.getOneChat(Long.parseLong(id));
		ChatMessage msg = new ChatMessage(cRoom, user, HtmlUtils.htmlEscape(message.getName()), MessageStatus.DELIVERED);
		this.cService.createMessage(msg);
		Greeting pyload = new Greeting(msg.getContent(), msg.getSender().getFullName(), msg.getSender().getPic(), msg.getTimestamp(), msg.getStatus());
		return pyload;
	}
}
