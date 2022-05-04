package ies.sotero.cstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ies.sotero.cstore.model.Product;
import ies.sotero.cstore.service.ProductService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public String home(Model model) {
		List<Product> products = productService.findAll();
		
		model.addAttribute("products", products);
		
		return "administrator/home";
	}
}
