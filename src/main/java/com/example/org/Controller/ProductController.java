package com.example.org.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.org.Service.ProductService;
import com.example.org.modal.Product;

@RestController
@RequestMapping
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/")
	public ResponseEntity<String> welcome(){
		return new ResponseEntity<>("Welcome to Best Store where BEST PRICE mets BEST QUALITY", HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id){
		Product product= service.getProduct(id);
		if (product!=null) {
		return new ResponseEntity<>(product, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	@GetMapping("/products")
	public ResponseEntity<List<Product>>getAllProducts(){
		
		List<Product> products = service.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		try {
		Product product1 = service.addProduct(product);
		return new ResponseEntity<>(product1, HttpStatus.OK);
		}catch(Exception e) {
		return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
}
