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
//	public Date getReleaseDate() {
//		return releaseDate;
//	}
//
//	public void setReleaseDate(Date releaseDate) {
//		this.releaseDate = releaseDate;
//	}
//
//	public boolean isProductAvailable() {
//		return productAvailable;
//	}
//
//	public void setProductAvailable(boolean productAvailable) {
//		this.productAvailable = productAvailable;
//	}
//
//	public String getBrand() {
//		return brand;
//	}
//
//	public void setBrand(String brand) {
//		this.brand = brand;
//	}
//
//	public byte[] getImageData() {
//		return imageData;
//	}
//
//	public void setImageData(byte[] imageData) {
//		this.imageData = imageData;
//	}
//
//	public String getImageName() {
//		return imageName;
//	}
//
//	public void setImageName(String imageName) {
//		this.imageName = imageName;
//	}
//
//	public String getImageType() {
//		return imageType;
//	}
//
//	public void setImageType(String imageType) {
//		this.imageType = imageType;
//	}
//
//	private Date releaseDate;
//	private boolean productAvailable;
//	private String brand;
//	private byte[] imageData;
//	private String imageName;
//	private String imageType;

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

//@Override
//public String toString() {
//	return "Product [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", discountPercent="
//			+ discountPercent + ", releaseDate=" + releaseDate + ", productAvailable=" + productAvailable + ", brand="
//			+ brand + ", imageData=" + Arrays.toString(imageData) + ", imageName=" + imageName + ", imageType="
//			+ imageType + "]";
//}

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
