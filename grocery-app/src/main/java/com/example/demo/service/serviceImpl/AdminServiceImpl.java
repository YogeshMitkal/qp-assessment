package com.example.demo.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.GroceryItem;
import com.example.demo.repository.GroceryItemRepository;
import com.example.demo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public GroceryItem addGroceryItem(GroceryItem groceryItem) {

		try {
			return groceryItemRepository.save(groceryItem);
		} catch (Exception e) {
			logger.error("Error adding grocery item", e);
			throw new RuntimeException("Error adding grocery item", e);
		}

	}

	@Override
	public Iterable<GroceryItem> viewAllGroceryItems() {
		try {
			return groceryItemRepository.findAll();
		} catch (Exception e) {
			logger.error("Error viewing all grocery items", e);
			throw new RuntimeException("Error viewing all grocery items", e);
		}
	}

	@Override
	public void removeGroceryItem(int itemId) {

		try {
			groceryItemRepository.deleteById(itemId);
		} catch (Exception e) {
			logger.error("Error removing grocery item with ID: {}", itemId, e);
			throw new RuntimeException("Error removing grocery item", e);
		}
	}

	@Override
	public GroceryItem updateGroceryItem(int itemId, GroceryItem updatedItem) {

		try {
			GroceryItem existingItem = groceryItemRepository.findById(itemId).orElse(null);
			if (existingItem != null) {
				existingItem.setName(updatedItem.getName());
				existingItem.setPrice(updatedItem.getPrice());
				existingItem.setInventory(updatedItem.getInventory());
				return groceryItemRepository.save(existingItem);
			}
			return null;
		} catch (Exception e) {
			logger.error("Error updating grocery item with ID: {}", itemId, e);
			throw new RuntimeException("Error updating grocery item", e);
		}
	}

	@Override
	public void manageInventory(int itemId, int quantity) {

		try {
			GroceryItem item = groceryItemRepository.findById(itemId).orElse(null);
			if (item != null) {
				item.setInventory(item.getInventory() + quantity);
				groceryItemRepository.save(item);
			}
		} catch (Exception e) {
			logger.error("Error managing inventory for grocery item with ID: {}", itemId, e);
			throw new RuntimeException("Error managing inventory", e);
		}

	}
}
