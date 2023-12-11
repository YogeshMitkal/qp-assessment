package com.example.demo.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Booking;
import com.example.demo.entity.GroceryItem;
import com.example.demo.request.ItemsDto;

public interface UserService {
	Iterable<GroceryItem> viewAllGroceryItems();

	Booking bookGroceryItems(@RequestBody ItemsDto itemsDto);

}
