package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Integer> {
	
	Optional<GroceryItem> findById(int id);
}

