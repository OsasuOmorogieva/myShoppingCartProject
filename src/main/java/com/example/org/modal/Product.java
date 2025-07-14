package com.example.org.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	private int quantity;
	private double discountPercent;

//	public Product(String name, double price, int quantity, double discountPercent) {
//		this.name = name;
//		this.price = price;
//		this.quantity = quantity;
//		this.discountPercent = discountPercent;
//		
//
//	}
//	public Product() {
//		
//	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

	// public ArrayList<Product> addProduct(String name, String size, double price,
	// int quantity) {
//		ArrayList<Product> clothings =new ArrayList<>();
//		
//		clothings.add(new Product("Gucci","medium", 799.0,17));
//		clothings.add(new Product("Nike","large", 125.0,10));
//		clothings.add(new Product("Zara","small", 1799.60,107));
//		return clothings;
//	}
	@Override
	
	public String toString() {
	    return String.format("Product: %s | Price: $%.2f | Discount: %.0f%% | Quantity: %d\n", 
	                         name, price, discountPercent * 100, quantity);
	


}
}
