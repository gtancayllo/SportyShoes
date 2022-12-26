package com.sportyshoes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.entity.Product;
import com.sportyshoes.entity.PurchaseOrder;
import com.sportyshoes.service.ProductService;
import com.sportyshoes.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;

	@GetMapping("/browseUsers")
	public String browseUsers(Model model) {
		// create model object to store form data
		model.addAttribute("userList", userService.getSignedUpUsers());
		
		return "listuser";
	}
	
	@GetMapping("/manageProducts")
	public String manageProducts(Model model) {
		// create model object to store form data
		model.addAttribute("productList", productService.getAllProducts());
		return "listproduct";
	}
	
	@GetMapping("/purchaseOrders")
	public String purchaseOrders(Model model) {
		// create model object to store form data
		model.addAttribute("purchaseOrderList", productService.getAllPurchaseOrders());
		model.addAttribute("categories", productService.getAllCategories());
		return "listpurchaseorder";
	}
	
	@GetMapping("/searchOrders")
	public String searchOrders(Model model, @RequestParam("categoryName") String categoryName,@RequestParam("orderDate") String orderDate) {
		
		model.addAttribute("purchaseOrderList", productService.searchPurchaseOrders(categoryName,new java.util.Date()));
		model.addAttribute("categories", productService.getAllCategories());
		return "listpurchaseorder";
	}
	
	@GetMapping("/viewOrder")
	public String viewOrder(Model model, @RequestParam("id") int id) {
		PurchaseOrder purchaseOrder = productService.findPurchaseOrder(id);
		model.addAttribute("purchaseOrder", purchaseOrder);
		return "purchaseorder";
	}
	
	@GetMapping("/editProduct")
	public String editProduct(Model model, @RequestParam("id") int id) {
		Product product = null;
		if(id == -1) //new product
			product = new Product();
		else
			product = productService.findProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("categories", productService.getAllCategories());
		return "modifyProduct";
	}
	
	@GetMapping("/deleteProduct")
	public String deleteProduct(Model model, @RequestParam("id") int id) {
		Product product = productService.findProduct(id);
		productService.deleteProduct(product);
		return "redirect:/manageProducts";
	}
	
	@PostMapping("/product/save")
	public String registration(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
		productService.saveProduct(product);
		return "redirect:/manageProducts";
	}
	
}