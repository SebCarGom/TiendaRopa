package ies.sotero.cstore.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ies.sotero.cstore.model.User;
import ies.sotero.cstore.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String create() {
		return "user/register";
	}
	
	@PostMapping("/save")
	public String save(User user) {
		LOGGER.info("User register: {}", user);
		
		user.setType("USER");
		
		userService.save(user);
		
		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@PostMapping("/access")
	public String access(User user, HttpSession session) {
		LOGGER.info("Access: {}", user);
		
		Optional<User> userOptional = userService.finByEmail(user.getEmail());

		if(userOptional.isPresent()) {
			session.setAttribute("userId", userOptional.get().getId());
			
			if(userOptional.get().getType().equals("ADMIN")) {
				return "redirect:/administrator";
			} else {
				return "redirect:/";
			}
		} else {
			LOGGER.info("User does not exist");
		}
		
		return "redirect:/";
	}
}
