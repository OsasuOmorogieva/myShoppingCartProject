package com.example.org.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.org.Service.CartService;
import com.example.org.modal.CartItem;

@RestController
@RequestMapping
@CrossOrigin
public class CartController {
	@Autowired
	private CartService service;
	
	@PostMapping("/products/addProduct")
	public ResponseEntity<?>addProductToCart(@RequestBody CartItem cartItem){
		try {
		String newProduct= service.addProductToCart(cartItem.getName(), cartItem.getQuantity());
		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>("Please enter a valid product quantity", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/products/cart")
	public ResponseEntity<?> getCart(){
		List<CartItem> productCart = service.getCart();
		if (productCart.isEmpty()) {
			return new ResponseEntity<>("Your cart is currently empty, please add product to cart", HttpStatus.OK);
		}else {
		return new ResponseEntity<>(productCart, HttpStatus.OK);
		
	}
	}
	@PostMapping("/products/removeProduct")
	public ResponseEntity<String> removeFromCart(@RequestBody CartItem cartItem){
		String removeItem = service.removeFromCart(cartItem.getName(), cartItem.getQuantity());
		return new ResponseEntity<>(removeItem, HttpStatus.OK);
	}
	
@PostMapping("/products/updateOrder")
public ResponseEntity<String> updateOrder(@RequestBody CartItem cartItem){
	String updatedCart = service.updateCart(cartItem);
	return new ResponseEntity<>(updatedCart, HttpStatus.OK);
}

@GetMapping("/products/receipt")
public ResponseEntity<String> getReceipt(CartItem cartItem){
	String receipt = service.getReceipt(cartItem);
	return new ResponseEntity<>(receipt, HttpStatus.CREATED);
}
}
