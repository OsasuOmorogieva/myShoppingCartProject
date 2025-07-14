package com.example.org.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.org.Repository.CartRepository;
import com.example.org.Repository.ProductRepository;
import com.example.org.modal.CartItem;
import com.example.org.modal.Product;
@Service
public class CartService {
	
	@Autowired
	private ProductRepository repo;
	@Autowired
	private CartRepository cartRepo;
	
	CartItem cart = new CartItem();

	//private List<Product> inventory = new ArrayList<>();
	//private HashMap<String, Integer> cart = new HashMap<>();
	
	public List<CartItem> getCart() {
		cartRepo.findAll();
		return cartRepo.findAll();
		
	}
	public  String addProductToCart(String productName, int qty) {
		Optional<Product> productOpt = repo.findByNameIgnoreCase(productName);
		if(productOpt.isEmpty()) {
			return "opps product current not available in store";
		}
		Product product = productOpt.get();
		if(product.getQuantity()==0) {
			return productName + " is currently out of stock, please check back later.";
		}
		else if(qty<=0) {
			return "Please enter a valid quantity";
		}
		else if(qty>product.getQuantity()) {
			return "Insufficient quantity of " + productName
					+ " in stock, please order a lesser quantity"; 
		}
		Optional<CartItem> existingCartItem = cartRepo.findByNameIgnoreCase(productName);
		if(existingCartItem.isPresent()) {
			cart = existingCartItem.get();
			cart.setQuantity(cart.getQuantity()+qty);
		}else {
			cart = new CartItem();
		cart.setName(productName);
		cart.setQuantity(qty);
		}
		cartRepo.save(cart);
		product.setQuantity(product.getQuantity()-qty);
		repo.save(product);
		return qty + " " + productName + "(s) added to cart.";

	}
	public String removeFromCart(String productName, int qty) {
		if(cartRepo.findAll().isEmpty()) {
			return "Your cart is currently empty.";
		}
		Optional<CartItem> cartOpt = cartRepo.findByNameIgnoreCase(productName);
		if(cartOpt.isEmpty()) {
			return "product not available in cart";
		} else {
			CartItem removeItem =cartOpt.get();
			int currentQty = removeItem.getQuantity();
			if (qty <= 0) {
				return " please enter a valid quantity";
			}
			if(qty > currentQty) {
				return "insufficient quantity of " + removeItem.getName() + " in cart.";
			} else if (qty ==currentQty) {
				cartRepo.deleteAllByNameIgnoreCase(productName);
				return removeItem.getName() + " successfully removed from cart.";
			} else if (qty < currentQty) {
				removeItem.setQuantity(currentQty - qty);
				cartRepo.save(removeItem);
				return qty + " of " + removeItem.getName()+"(s) successfully removed from cart";
			}
			else {
				return "invalid input";
			}
		}
	}
	public String updateCart(CartItem cartItem) {
		CartItem oldCartItem = cartRepo.findByNameIgnoreCase(cartItem.getName()).get();
		oldCartItem.setQuantity(cartItem.getQuantity());
		cartRepo.save(oldCartItem);
		return oldCartItem.getName() + " order updated successfully";
		
		
	}

		public String getReceipt(CartItem cartItem) {
			String name="";
			double price=0;
			int qty=0;
			double costAfterDiscountPerProduct =0;
			double discountPerProduct =0;
			double discount=0;
			double discountPercent=0;
			double totalDiscount = 0;
			double itemCost=0;
			double subTotal =0;
			double total= 0;
			double taxRate = 0.13;
			double tax=0;
			double totalTax =0;
			double grandTotal =0;
			StringBuilder receipt = new StringBuilder();
			List<CartItem>cart = cartRepo.findByStatus(name);
			if(cart.isEmpty()) {
				return "you can not generate reciept for empty cart. Please add product to cart"; 
			}else {
				

				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");

				String timeStamp = now.format(formatter);

				receipt.append("\n%-%-%-%=========================================%-%-%-%\n");
				receipt.append("Date: " + timeStamp).append("\n");
				receipt.append(String.format("%-15s %-10s %-10s %-10s%n", "Product", "Qty", "Unit Price", "Total"));
				receipt.append("-------------------------------------------------------\n");
				for (CartItem item: cart) {
					name = item.getName();
					qty =item.getQuantity();
					price = repo.findByNameIgnoreCase(name).get().getPrice();
					discountPercent = repo.findByNameIgnoreCase(name).get().getDiscountPercent();
					if(discountPercent==0) {
						 discount = 0;
					}else {
					discount = discountPercent * price;
					}
					itemCost = qty * price;
					discountPerProduct = qty * discount;
					costAfterDiscountPerProduct = itemCost - discountPerProduct;
					tax = taxRate * costAfterDiscountPerProduct;
					receipt.append(String.format("%-15s %-10d %-9.2f %-9.2f%n", name, qty, price,
							itemCost));
					receipt.append("-------------------------------------------------------\n");
					
				}
				subTotal+=itemCost;
				totalDiscount +=discountPerProduct;
				total +=costAfterDiscountPerProduct;
				totalTax += tax;
				grandTotal = total + totalTax;
				
				receipt.append(String.format("%-36s $%-9.2f%n", "Tax Total:", totalTax));
				receipt.append(String.format("%-36s $%-9.2f%n", "Subtotal:", subTotal));
				receipt.append(String.format("%-36s $%-9.2f%n", "Total Discount:", totalDiscount));
				receipt.append("-------------------------------------------------------\n");
				receipt.append(String.format("%-36s $%-9.2f%n", "Grand Total:", grandTotal));
				receipt.append(String.format("=======================================================\n"));
				receipt.append("           Its all about the best quality         ");

			}
			
			 return receipt.toString();
		}
	}


