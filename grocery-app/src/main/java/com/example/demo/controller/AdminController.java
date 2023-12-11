package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//src/main/java/com/example/grocerybookingapi/controller/AdminController.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.GroceryItem;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@PostMapping("/add")
	public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItem groceryItem) {

		try {
			GroceryItem addedItem = adminService.addGroceryItem(groceryItem);
			logger.info("Added grocery item with ID: {}", addedItem.getId());
			return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error adding grocery item", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error adding grocery item", e);
		}
	}

	@GetMapping("/view")
	public ResponseEntity<Iterable<GroceryItem>> viewAllGroceryItems() {

		try {
			Iterable<GroceryItem> groceryItems = adminService.viewAllGroceryItems();
			logger.info("Retrieved all grocery items");
			return new ResponseEntity<>(groceryItems, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error retrieving grocery items", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving grocery items", e);
		}
	}

	@DeleteMapping("/remove/{itemId}")
	public ResponseEntity<Void> removeGroceryItem(@PathVariable int itemId) {

		try {
			adminService.removeGroceryItem(itemId);
			logger.info("Removed grocery item with ID: {}", itemId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error("Error removing grocery item with ID: {}", itemId, e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error removing grocery item", e);
		}
	}

	@PutMapping("/update/{itemId}")
	public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable int itemId,
			@RequestBody GroceryItem updatedItem) {

		try {
			GroceryItem result = adminService.updateGroceryItem(itemId, updatedItem);
			if (result != null) {
				logger.info("Updated grocery item with ID: {}", itemId);
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				logger.warn("Grocery item with ID {} not found", itemId);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Error updating grocery item with ID: {}", itemId, e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating grocery item", e);
		}
	}

	@PutMapping("/manage-inventory/{itemId}")
	public ResponseEntity<Void> manageInventory(@PathVariable int itemId, @RequestParam int quantity) {

		try {
			adminService.manageInventory(itemId, quantity);
			logger.info("Managed inventory for grocery item with ID: {}", itemId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error managing inventory for grocery item with ID: {}", itemId, e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error managing inventory", e);
		}
	}
}
