package com.example.org.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.org.modal.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {
	Optional<CartItem> findByNameIgnoreCase(String name);
	Optional<CartItem> deleteAllByNameIgnoreCase(String name);
	List<CartItem> findByStatus(String status);

}
