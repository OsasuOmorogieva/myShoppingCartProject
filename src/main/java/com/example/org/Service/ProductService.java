package com.example.org.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.org.Repository.ProductRepository;
import com.example.org.modal.Product;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	

	public Product getProduct(int id) {
		return repo.findById(id).orElse(new Product());
	}


	public List<Product> getAllProducts() {
		
		return repo.findAll();
	}


	public Product addProduct(Product product1) {
		Optional<Product>  currentProduct = repo.findByNameIgnoreCase(product1.getName());
		if(currentProduct.isEmpty()) {
		repo.save(product1);
		return product1;
	}else {
		Product currentProduct1 = currentProduct.get();
		currentProduct1.setQuantity(currentProduct1.getQuantity() + product1.getQuantity());
		repo.save(currentProduct1);
		return currentProduct1;
	}

}


	public List<Product> searchProducts(String keyword) {
		
		return repo.searchProduct(keyword) ;
	}
}
