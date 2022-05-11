package ies.sotero.cstore.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ies.sotero.cstore.model.Order;
import ies.sotero.cstore.model.User;
import ies.sotero.cstore.service.IOrderService;
import ies.sotero.cstore.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrderService orderService;
	
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
	
	@GetMapping("/purchases")
	public String getPurchases (Model model, HttpSession session) {
		model.addAttribute("session", session.getAttribute("userId"));
		
		User user = userService.findbyId(Integer.parseInt(session.getAttribute("userId").toString())).get();
		
		List<Order> orders = orderService.findByUser(user);

		model.addAttribute("orders", orders);
		
		return "user/purchases";
	}
	
	@GetMapping("/detail/{id}")
	public String purchaseDetail(@PathVariable Integer id, HttpSession session, Model model) {
		LOGGER.info("Order id: {}", id);
		
		Optional<Order> order = orderService.findById(id);
		
		model.addAttribute("details", order.get().getOrderDetail());
		
		model.addAttribute("session", session.getAttribute("userId"));
		
		return "user/purchase_detail";
	}
	
	@GetMapping("/logout")
	public String sessionLogOut(HttpSession session) {
		
		session.removeAttribute("userId");
		
		return "redirect:/";
	}
}
