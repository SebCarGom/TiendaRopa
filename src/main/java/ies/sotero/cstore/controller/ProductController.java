package ies.sotero.cstore.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ies.sotero.cstore.model.Product;
import ies.sotero.cstore.model.User;
import ies.sotero.cstore.service.IProductService;
import ies.sotero.cstore.service.IUserService;
import ies.sotero.cstore.service.UploadFileService;
import ies.sotero.cstore.service.UserServiceImpl;

@Controller
@RequestMapping("/products")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private IProductService productService;

	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private IUserService userService;

	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("products", productService.findAll());
		return "products/show";
	}

	@GetMapping("/create")
	public String create() {
		return "products/create";
	}

	@PostMapping("/save")
	public String save(Product product, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		LOGGER.info("Object Product {}", product);

		User u = userService.findbyId(Integer.parseInt(session.getAttribute("userId").toString())).get();

		product.setUser(u);

		if (product.getId() == null) {
			String imageName = uploadFileService.saveImage(file);

			product.setImage(imageName);
		}

		productService.save(product);

		return "redirect:/products";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Product product = new Product();

		Optional<Product> optionalProduct = productService.get(id);

		product = optionalProduct.get();

		LOGGER.info("Product searched: {}", product);

		model.addAttribute("product", product);

		return "products/edit";
	}

	@PostMapping("/update")
	public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException {
		Product p = new Product();

		p = productService.get(product.getId()).get();
		
		if (file.isEmpty()) {
			product.setImage(p.getImage());
		} else {
			if (!p.getImage().equals("default.jpg")) {
				uploadFileService.deleteImage(p.getImage());
			}

			String imageName = uploadFileService.saveImage(file);

			product.setImage(imageName);
		}
		
		product.setUser(p.getUser());

		productService.update(product);

		return "redirect:/products";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {

		Product p = new Product();

		p = productService.get(id).get();

		if (!p.getImage().equals("default.jpg")) {
			uploadFileService.deleteImage(p.getImage());
		}

		productService.delete(id);

		return "redirect:/products";
	}
}
