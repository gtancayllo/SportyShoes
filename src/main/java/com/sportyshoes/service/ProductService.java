package com.sportyshoes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.entity.Category;
import com.sportyshoes.entity.Product;
import com.sportyshoes.entity.PurchaseOrder;
import com.sportyshoes.repository.CategoryRepository;
import com.sportyshoes.repository.ProductRepository;
import com.sportyshoes.repository.PurchaseOrderRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	                                                    
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		productRepository.findAll().forEach(m-> products.add(m));
		return products;
	}
	
	public List<PurchaseOrder> getAllPurchaseOrders(){
		List<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
		purchaseOrderRepository.findAll().forEach(m-> purchaseOrders.add(m));
		return purchaseOrders;
	}
	
	public List<PurchaseOrder> searchPurchaseOrders(String categoryName, java.util.Date orderDate){
		List<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
		purchaseOrderRepository.findAll().stream().filter(p->p.getOrderDate().compareTo(orderDate)==0 && p.getCategories().indexOf(categoryName)>=0).forEach(purchaseorder-> purchaseOrders.add(purchaseorder));
		return purchaseOrders;
	}
	
	public List<Category> getAllCategories(){
		List<Category> categories = new ArrayList<Category>();
		categoryRepository.findAll().forEach(c->categories.add(c));
		return categories;
	}
	
	public Product findProduct(int productId) {
		 Optional<Product> optionalProduct = productRepository.findById(productId);
		 if(optionalProduct.isPresent())
		 	 return optionalProduct.get();
		 return null;
	}
	
	public PurchaseOrder findPurchaseOrder(int id) {
		 Optional<PurchaseOrder> optionalPO = purchaseOrderRepository.findById(id);
		 if(optionalPO.isPresent())
		 	 return optionalPO.get();
		 return null;
	}
	
	public void saveProduct(Product product) {
        productRepository.save(product);
    }
	
	public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}