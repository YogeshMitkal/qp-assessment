package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Booking;
import com.example.demo.entity.GroceryItem;
import com.example.demo.request.ItemsDto;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/view")
	public ResponseEntity<Iterable<GroceryItem>> viewAllGroceryItems() {

		try {
			Iterable<GroceryItem> groceryItems = userService.viewAllGroceryItems();
			logger.info("Retrieved all grocery items");
			return new ResponseEntity<>(groceryItems, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error retrieving all grocery items", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/book")
	public ResponseEntity<Booking> bookGroceryItems(@RequestBody ItemsDto itemsDto) {

		try {
			logger.info("in try");
			Booking booking = userService.bookGroceryItems(itemsDto);

			if (booking != null) {
				logger.info("Successfully booked grocery items with IDs: {}");
				return new ResponseEntity<>(booking, HttpStatus.CREATED);
			} else {
				logger.warn("Grocery items with IDs {} not found for booking");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Error booking grocery items", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
