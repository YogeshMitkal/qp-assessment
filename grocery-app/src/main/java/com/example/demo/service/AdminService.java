package com.example.demo.service;

import com.example.demo.entity.GroceryItem;

public interface AdminService {
	GroceryItem addGroceryItem(GroceryItem groceryItem);

	Iterable<GroceryItem> viewAllGroceryItems();

	void removeGroceryItem(int itemId);

	GroceryItem updateGroceryItem(int itemId, GroceryItem updatedItem);

	void manageInventory(int itemId, int quantity);
}
