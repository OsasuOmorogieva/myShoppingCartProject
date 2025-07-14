package com.example.org.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.org.modal.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findByNameIgnoreCase(String name);
	

}
